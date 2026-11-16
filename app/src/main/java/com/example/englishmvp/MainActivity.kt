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
import com.example.englishmvp.ui.HomeScreen
import com.example.englishmvp.ui.LessonScreen
import com.example.englishmvp.ui.ResultScreen
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
                    val completed = LessonRepository.lessons.associate { lesson ->
                        val done by store.isCompleted(lesson.id).collectAsState(initial = false)
                        lesson.id to done
                    }
                    var lastResult by remember { mutableStateOf<Triple<Int, Int, Int>?>(null) }

                    NavHost(navController = nav, startDestination = "home") {
                        composable("home") {
                            HomeScreen(xp = xp, completed = completed, onOpenLesson = { nav.navigate("lesson/$it") })
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
                                onBack = { nav.popBackStack() }
                            )
                        }
                        composable("result") {
                            val r = lastResult
                            if (r != null) ResultScreen(r.first, r.second, r.third) {
                                nav.navigate("home") { popUpTo("home") { inclusive = true } }
                            }
                        }
                    }
                }
            }
        }
    }
}
