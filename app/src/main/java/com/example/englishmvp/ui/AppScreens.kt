package com.example.englishmvp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.englishmvp.data.Lesson
import com.example.englishmvp.data.LessonRepository
import com.example.englishmvp.data.Question
import com.example.englishmvp.data.Song
import com.example.englishmvp.data.SongRepository
import com.example.englishmvp.data.SoundHelper
import com.example.englishmvp.data.TtsHelper

private fun normalize(s: String): String =
    s.lowercase().trim()
        .replace(Regex("[!¡?¿.,;:]"), "")
        .replace(Regex("\\s+"), " ")

private fun correctTextOf(q: Question): String =
    if (q.type == "write") q.answer.split("|").first() else q.options[q.correctIndex]

private fun isAnswerCorrect(q: Question, selected: Int?, written: String): Boolean =
    if (q.type == "write")
        normalize(written) in q.answer.split("|").map { normalize(it) }
    else
        selected == q.correctIndex

@Composable
fun SongScreen(song: com.example.englishmvp.data.Song, onBack: () -> Unit) {
    val context = LocalContext.current
    val tts = remember { TtsHelper(context) }
    DisposableEffect(Unit) { onDispose { tts.shutdown() } }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("←") }
            Text(song.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        Text("Toca 🔊 para escuchar cada línea en inglés", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(song.lines.size) { i ->
                val line = song.lines[i]
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(line.english, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                            IconButton(onClick = { tts.speak(line.english) }) { Text("🔊") }
                        }
                        Text(line.spanish, style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(xp: Int, completed: Map<String, Boolean>, streak: Int, failedCount: Int, onOpenLesson: (String) -> Unit, onOpenSong: (String) -> Unit, onOpenReview: () -> Unit, onReset: () -> Unit) {
    val allDone = LessonRepository.lessons.all { completed[it.id] == true }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (allDone) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🎓", style = MaterialTheme.typography.displayMedium)
                    Text("¡Curso completado!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Terminaste las ${LessonRepository.lessons.size} lecciones. ¡Excelente trabajo!",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = onReset) { Text("Reiniciar progreso") }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        Text("Aprende inglés", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        if (failedCount > 0) {
            Card(onClick = onOpenReview, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🔁", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Repasar errores", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("$failedCount preguntas falladas pendientes", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("🔥 Racha: $streak ${if (streak == 1) "día" else "días"}   ·   XP total: $xp",
                    style = MaterialTheme.typography.titleMedium)
                Text("Lecciones completadas: ${completed.values.count { it }} / ${LessonRepository.lessons.size}")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                Text("📚 Lecciones", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            itemsIndexed(LessonRepository.lessons) { i, lesson ->
                val done = completed[lesson.id] == true
                val unlocked = i == 0 || completed[LessonRepository.lessons[i - 1].id] == true
                Card(
                    onClick = { if (unlocked) onOpenLesson(lesson.id) },
                    enabled = unlocked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .background(
                                    when {
                                        done -> MaterialTheme.colorScheme.primary
                                        unlocked -> MaterialTheme.colorScheme.secondaryContainer
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    },
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                if (done) "✓" else if (unlocked) "${i + 1}" else "🔒",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(lesson.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                when {
                                    done -> "✓ Completada"
                                    unlocked -> lesson.description
                                    else -> "Completa la lección anterior para desbloquear"
                                },
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                Text("🎵 Canciones", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            items(SongRepository.songs) { song ->
                Card(onClick = { onOpenSong(song.id) }, modifier = Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(52.dp).background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) { Text("🎵", style = MaterialTheme.typography.titleLarge) }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(song.title, style = MaterialTheme.typography.titleMedium)
                            Text("Letra con pronunciación y traducción", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LessonScreen(lesson: Lesson, onFinish: (Int, Int) -> Unit, onBack: () -> Unit, onFail: (String) -> Unit = {}) {
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Int?>(null) }
    var textInput by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }
    var correctNow by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    val q = lesson.questions[index]
    val context = LocalContext.current
    val tts = remember { TtsHelper(context) }
    val sounds = remember { SoundHelper() }
    DisposableEffect(Unit) {
        onDispose {
            tts.shutdown()
            sounds.release()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LinearProgressIndicator(
            progress = { (index + if (answered) 1 else 0).toFloat() / lesson.questions.size },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(lesson.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Pregunta ${index + 1} de ${lesson.questions.size}")
        Spacer(Modifier.height(16.dp))
        Text(q.prompt, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        if (q.type == "write") {
            OutlinedTextField(
                value = textInput,
                onValueChange = { if (!answered) textInput = it },
                label = { Text("Escribe en inglés") },
                singleLine = true,
                enabled = !answered,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(4.dp))
            Text("Puedes ignorar mayúsculas y signos", style = MaterialTheme.typography.bodySmall)
        } else {
            q.options.forEachIndexed { i, opt ->
                val isCorrect = i == q.correctIndex
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    when {
                        answered && isCorrect -> Button(onClick = {}, modifier = Modifier.weight(1f), enabled = false) { Text(opt) }
                        answered && selected == i -> Button(onClick = {}, modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error), enabled = false) { Text(opt) }
                        else -> OutlinedButton(onClick = { selected = i }, modifier = Modifier.weight(1f), enabled = !answered) { Text(opt) }
                    }
                    IconButton(onClick = { tts.speak(opt) }) { Text("🔊") }
                }
            }
        }

        Spacer(Modifier.weight(1f))
        LaunchedEffect(index, answered) {
            if (answered) {
                if (correctNow) {
                    sounds.success()
                    tts.speak(correctTextOf(q))
                } else {
                    sounds.error()
                }
            }
        }
        if (answered) {
            if (correctNow) {
                Text("¡Correcto! 🎉", color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            } else {
                Text("Incorrecto ❌ La respuesta era: ${correctTextOf(q)}",
                    color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                if (index < lesson.questions.lastIndex) {
                    index++; selected = null; textInput = ""; answered = false; correctNow = false
                } else onFinish(correctCount, lesson.questions.size)
            }, modifier = Modifier.fillMaxWidth()) { Text(if (index < lesson.questions.lastIndex) "Siguiente" else "Finalizar") }
        } else {
            Button(onClick = {
                val ok = isAnswerCorrect(q, selected, textInput)
                answered = true
                correctNow = ok
                if (ok) correctCount++ else onFail("${lesson.id}:$index")
            }, enabled = if (q.type == "write") textInput.isNotBlank() else selected != null,
                modifier = Modifier.fillMaxWidth()) { Text("Comprobar") }
        }
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Salir") }
    }
}

@Composable
fun ResultScreen(correct: Int, total: Int, gainedXp: Int, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🏆", style = MaterialTheme.typography.displayLarge)
        Text("¡Lección completada!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Aciertos: $correct / $total", style = MaterialTheme.typography.titleLarge)
        Text("XP ganada: +$gainedXp", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onDone) { Text("Continuar") }
    }
}

@Composable
fun ReviewScreen(
    questions: List<Pair<Lesson, Int>>,
    onResolved: (String) -> Unit,
    onExit: () -> Unit
) {
    var idx by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Int?>(null) }
    var textInput by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }
    var correctNow by remember { mutableStateOf(false) }
    var resolvedCount by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val tts = remember { TtsHelper(context) }
    val sounds = remember { SoundHelper() }
    DisposableEffect(Unit) { onDispose { tts.shutdown(); sounds.release() } }

    if (questions.isEmpty()) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🎉", style = MaterialTheme.typography.displayLarge)
            Text("No tienes errores pendientes", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onExit) { Text("Volver") }
        }
        return
    }

    val queue = remember { questions.shuffled() }
    if (finished) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("✅", style = MaterialTheme.typography.displayLarge)
            Text("Repaso terminado", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Corregiste $resolvedCount de ${queue.size} preguntas", style = MaterialTheme.typography.titleMedium)
            Text("Las que fallaste seguirán pendientes para el próximo repaso", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onExit) { Text("Volver") }
        }
        return
    }

    val (lesson, qIndex) = queue[idx]
    val q = lesson.questions[qIndex]
    val key = "${lesson.id}:$qIndex"

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("🔁 Repaso de errores", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Pregunta ${idx + 1} de ${queue.size}")
        Spacer(Modifier.height(16.dp))
        Text(q.prompt, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        if (q.type == "write") {
            OutlinedTextField(
                value = textInput,
                onValueChange = { if (!answered) textInput = it },
                label = { Text("Escribe en inglés") },
                singleLine = true,
                enabled = !answered,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            q.options.forEachIndexed { i, opt ->
                val isCorrect = i == q.correctIndex
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    when {
                        answered && isCorrect -> Button(onClick = {}, modifier = Modifier.weight(1f), enabled = false) { Text(opt) }
                        answered && selected == i -> Button(onClick = {}, modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error), enabled = false) { Text(opt) }
                        else -> OutlinedButton(onClick = { selected = i }, modifier = Modifier.weight(1f), enabled = !answered) { Text(opt) }
                    }
                    IconButton(onClick = { tts.speak(opt) }) { Text("🔊") }
                }
            }
        }

        Spacer(Modifier.weight(1f))
        LaunchedEffect(idx, answered) {
            if (answered) {
                if (correctNow) { sounds.success(); tts.speak(correctTextOf(q)) } else sounds.error()
            }
        }
        if (answered) {
            if (correctNow) {
                Text("¡Correcto! Ya no la verás en el repaso 🎉", color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            } else {
                Text("Incorrecto ❌ Queda pendiente. La respuesta era: ${correctTextOf(q)}",
                    color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                if (idx < queue.lastIndex) {
                    idx++; selected = null; textInput = ""; answered = false; correctNow = false
                } else finished = true
            }, modifier = Modifier.fillMaxWidth()) { Text(if (idx < queue.lastIndex) "Siguiente" else "Terminar repaso") }
        } else {
            Button(onClick = {
                val ok = isAnswerCorrect(q, selected, textInput)
                answered = true
                correctNow = ok
                if (ok) { onResolved(key); resolvedCount++ }
            }, enabled = if (q.type == "write") textInput.isNotBlank() else selected != null,
                modifier = Modifier.fillMaxWidth()) { Text("Comprobar") }
        }
        TextButton(onClick = onExit, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Salir") }
    }
}
