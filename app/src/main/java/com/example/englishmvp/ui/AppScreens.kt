package com.example.englishmvp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.englishmvp.data.Lesson
import com.example.englishmvp.data.LessonRepository

@Composable
fun HomeScreen(xp: Int, completed: Map<String, Boolean>, streak: Int, onOpenLesson: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Aprende inglés", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("🔥 Racha: $streak ${if (streak == 1) "día" else "días"}   ·   XP total: $xp",
                    style = MaterialTheme.typography.titleMedium)
                Text("Lecciones completadas: ${completed.values.count { it }} / ${LessonRepository.lessons.size}")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(LessonRepository.lessons) { lesson ->
                val done = completed[lesson.id] == true
                Card(onClick = { onOpenLesson(lesson.id) }, modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(lesson.title, style = MaterialTheme.typography.titleLarge)
                        Text(lesson.description, style = MaterialTheme.typography.bodyMedium)
                        Text(if (done) "✓ Completada" else "Pendiente",
                            color = if (done) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                    }
                }
            }
        }
    }
}

@Composable
fun LessonScreen(lesson: Lesson, onFinish: (Int, Int) -> Unit, onBack: () -> Unit) {
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Int?>(null) }
    var answered by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    val q = lesson.questions[index]

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(lesson.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Pregunta ${index + 1} de ${lesson.questions.size}")
        Spacer(Modifier.height(16.dp))
        Text(q.prompt, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        q.options.forEachIndexed { i, opt ->
            val isCorrect = i == q.correctIndex
            when {
                answered && isCorrect -> Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), enabled = false) { Text(opt) }
                answered && selected == i -> Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error), enabled = false) { Text(opt) }
                else -> OutlinedButton(onClick = { selected = i }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), enabled = !answered) { Text(opt) }
            }
        }
        Spacer(Modifier.weight(1f))
        if (answered) {
            if (selected == q.correctIndex) {
                Text("¡Correcto! 🎉", color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            } else {
                Text("Incorrecto ❌ La respuesta era: ${q.options[q.correctIndex]}",
                    color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                if (index < lesson.questions.lastIndex) { index++; selected = null; answered = false }
                else onFinish(correctCount, lesson.questions.size)
            }, modifier = Modifier.fillMaxWidth()) { Text(if (index < lesson.questions.lastIndex) "Siguiente" else "Finalizar") }
        } else {
            Button(onClick = { if (selected != null) { answered = true; if (selected == q.correctIndex) correctCount++ } },
                enabled = selected != null, modifier = Modifier.fillMaxWidth()) { Text("Comprobar") }
        }
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Salir") }
    }
}

@Composable
fun ResultScreen(correct: Int, total: Int, gainedXp: Int, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¡Lección completada!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Aciertos: $correct / $total", style = MaterialTheme.typography.titleLarge)
        Text("XP ganada: +$gainedXp", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onDone) { Text("Continuar") }
    }
}
