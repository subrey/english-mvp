package com.example.englishmvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishmvp.data.LessonRepository
import com.example.englishmvp.data.ProgressStore
import com.example.englishmvp.data.SongRepository
import com.example.englishmvp.ui.HomeScreen
import com.example.englishmvp.ui.LessonScreen
import com.example.englishmvp.ui.ResultScreen
import com.example.englishmvp.ui.ReviewScreen
import com.example.englishmvp.ui.SongScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val store = ProgressStore(applicationContext)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val nav = rememberNavController()
                    val scope = rememberCoroutineScope()
                    val xp by store.xp.collectAsState(initial = 0)
                    val streak by store.streak.collectAsState(initial = 0)
                    val failed by store.failed.collectAsState(initial = emptySet<String>())
                    val failedQuestions = failed.mapNotNull { key ->
                        val parts = key.split(":")
                        val lesson = LessonRepository.lessons.find { it.id == parts[0] }
                        val qi = parts.getOrNull(1)?.toIntOrNull()
                        if (lesson != null && qi != null && qi in lesson.questions.indices) lesson to qi else null
                    }
                    val completed = LessonRepository.lessons.associate { lesson ->
                        val done by store.isCompleted(lesson.id).collectAsState(initial = false)
                        lesson.id to done
                    }
                    var lastResult by remember { mutableStateOf<Triple<Int, Int, Int>?>(null) }

                    NavHost(navController = nav, startDestination = "home") {
                        composable("home") {
                            HomeScreen(xp = xp, completed = completed, streak = streak,
                                failedCount = failedQuestions.size,
                                onOpenLesson = { nav.navigate("lesson/$it") },
                                onOpenSong = { nav.navigate("song/$it") },
                                onOpenReview = { nav.navigate("review") },
                                onReset = { scope.launch { store.resetAll() } })
                        }
                        composable("lesson/{id}") { backStack ->
                            val id = backStack.arguments?.getString("id") ?: return@composable
                            val lesson = LessonRepository.lessons.find { it.id == id } ?: return@composable
                            LessonScreen(
                                lesson = lesson,
                                onFinish = { correct, total ->
                                    val gained = correct * 10
                                    scope.launch { store.completeLesson(id, gained) }
                                    lastResult = Triple(correct, total, gained)
                                    nav.navigate("result") { popUpTo("home") }
                                },
                                onBack = { nav.popBackStack() },
                                onFail = { key -> scope.launch { store.addFailed(key) } }
                            )
                        }
                        composable("result") {
                            val r = lastResult
                            if (r != null) ResultScreen(r.first, r.second, r.third) {
                                nav.navigate("home") { popUpTo("home") { inclusive = true } }
                            }
                        }
                        composable("song/{id}") { backStack ->
                            val id = backStack.arguments?.getString("id") ?: return@composable
                            val song = SongRepository.songs.find { it.id == id } ?: return@composable
                            SongScreen(song = song, onBack = { nav.popBackStack() })
                        }
                        composable("review") {
                            ReviewScreen(
                                questions = failedQuestions,
                                onResolved = { key -> scope.launch { store.removeFailed(key) } },
                                onExit = { nav.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
