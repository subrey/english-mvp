package com.example.englishmvp.data

data class Question(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int
)

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val questions: List<Question>
)

object LessonRepository {
    val lessons: List<Lesson> = listOf(
        Lesson(
            id = "basics1",
            title = "Básicos 1",
            description = "Saludos y frases comunes",
            questions = listOf(
                Question("¿Cómo se dice \"Hola\" en inglés?", listOf("Bye", "Hello", "Please", "Thanks"), 1),
                Question("¿Cómo se dice \"Gracias\"?", listOf("Sorry", "Please", "Thank you", "Hello"), 2),
                Question("Selecciona la traducción de \"Buenos días\"", listOf("Good night", "Good morning", "Goodbye", "Good afternoon"), 1)
            )
        ),
        Lesson(
            id = "basics2",
            title = "Básicos 2",
            description = "Palabras esenciales",
            questions = listOf(
                Question("¿Qué significa \"Apple\"?", listOf("Pera", "Manzana", "Naranja", "Pan"), 1),
                Question("Completa: I ___ a student.", listOf("is", "are", "am", "be"), 2),
                Question("¿Cómo se dice \"Agua\"?", listOf("Water", "Fire", "Milk", "Juice"), 0)
            )
        ),
        Lesson(
            id = "verbs1",
            title = "Verbos 1",
            description = "Acciones comunes",
            questions = listOf(
                Question("¿Qué significa \"To eat\"?", listOf("Beber", "Dormir", "Comer", "Correr"), 2),
                Question("Traduce: \"I read a book\"", listOf("Yo leo un libro", "Yo escribo un libro", "Yo compro un libro", "Yo tengo un libro"), 0),
                Question("¿Cuál es el pasado de \"go\"?", listOf("Goed", "Went", "Gone", "Going"), 1)
            )
        )
    )
}
