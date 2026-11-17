package com.example.englishmvp.data

data class Question(
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctIndex: Int = -1,
    val type: String = "multiple", // "multiple" | "write"
    val answer: String = "" // para type="write"; alternativas separadas por |
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
                Question("Selecciona la traducción de \"Buenos días\"", listOf("Good night", "Good morning", "Goodbye", "Good afternoon"), 1),
                Question("Traduce al inglés: \"Buenas noches\"", type = "write", answer = "good night|goodnight")
            )
        ),
        Lesson(
            id = "basics2",
            title = "Básicos 2",
            description = "Palabras esenciales",
            questions = listOf(
                Question("¿Qué significa \"Apple\"?", listOf("Pera", "Manzana", "Naranja", "Pan"), 1),
                Question("Completa: I ___ a student.", listOf("is", "are", "am", "be"), 2),
                Question("¿Cómo se dice \"Agua\"?", listOf("Water", "Fire", "Milk", "Juice"), 0),
                Question("Traduce al inglés: \"Yo soy tu amigo\"", type = "write", answer = "i am your friend|im your friend")
            )
        ),
        Lesson(
            id = "verbs1",
            title = "Verbos 1",
            description = "Acciones comunes",
            questions = listOf(
                Question("¿Qué significa \"To eat\"?", listOf("Beber", "Dormir", "Comer", "Correr"), 2),
                Question("Traduce: \"I read a book\"", listOf("Yo leo un libro", "Yo escribo un libro", "Yo compro un libro", "Yo tengo un libro"), 0),
                Question("¿Cuál es el pasado de \"go\"?", listOf("Goed", "Went", "Gone", "Going"), 1),
                Question("Traduce al inglés: \"Yo bebo leche\"", type = "write", answer = "i drink milk")
            )
        ),
        Lesson(
            id = "colores",
            title = "Colores",
            description = "Los colores en inglés",
            questions = listOf(
                Question("¿Cómo se dice \"Rojo\"?", listOf("Blue", "Red", "Green", "White"), 1),
                Question("¿Qué significa \"Blue\"?", listOf("Azul", "Negro", "Amarillo", "Gris"), 0),
                Question("Traduce: \"El gato negro\"", listOf("The black cat", "The white cat", "The red cat", "The brown cat"), 0),
                Question("¿Cómo se dice \"Amarillo\"?", listOf("Yellow", "Purple", "Orange", "Pink"), 0),
                Question("Traduce al inglés: \"El cielo es azul\"", type = "write", answer = "the sky is blue")
            )
        ),
        Lesson(
            id = "numeros",
            title = "Números",
            description = "Del 1 al 10",
            questions = listOf(
                Question("¿Qué número es \"Seven\"?", listOf("6", "7", "8", "9"), 1),
                Question("¿Cómo se dice \"Cinco\"?", listOf("Four", "Six", "Five", "Three"), 2),
                Question("¿Qué significa \"Ten\"?", listOf("2", "10", "12", "20"), 1),
                Question("Completa: I have ___ brothers. (dos)", listOf("one", "two", "ten", "seven"), 1),
                Question("Traduce al inglés: \"Tengo tres gatos\"", type = "write", answer = "i have three cats")
            )
        ),
        Lesson(
            id = "familia",
            title = "Familia",
            description = "Miembros de la familia",
            questions = listOf(
                Question("¿Qué significa \"Mother\"?", listOf("Padre", "Madre", "Hermana", "Abuela"), 1),
                Question("¿Cómo se dice \"Hermano\"?", listOf("Sister", "Cousin", "Brother", "Uncle"), 2),
                Question("Traduce: \"Mi padre trabaja aquí\"", listOf("My father works here", "My mother works here", "My father lives here", "My father works there"), 0),
                Question("¿Qué significa \"Daughter\"?", listOf("Hijo", "Tía", "Hija", "Prima"), 2),
                Question("Traduce al inglés: \"Mi hermana es alta\"", type = "write", answer = "my sister is tall")
            )
        ),
        Lesson(
            id = "comida",
            title = "Comida",
            description = "Comidas y bebidas",
            questions = listOf(
                Question("¿Cómo se dice \"Pan\"?", listOf("Rice", "Bread", "Meat", "Cheese"), 1),
                Question("¿Qué significa \"Milk\"?", listOf("Leche", "Agua", "Café", "Vino"), 0),
                Question("Traduce: \"Me gusta el pollo\"", listOf("I like chicken", "I like fish", "I like rice", "I drink chicken"), 0),
                Question("¿Cómo se dice \"Huevo\"?", listOf("Egg", "Apple", "Sugar", "Salt"), 0),
                Question("Traduce al inglés: \"Quiero agua\"", type = "write", answer = "i want water")
            )
        ),
        Lesson(
            id = "frases",
            title = "Frases útiles",
            description = "Expresiones del día a día",
            questions = listOf(
                Question("¿Cómo se dice \"¿Cómo estás?\"?", listOf("Who are you?", "How are you?", "Where are you?", "What time is it?"), 1),
                Question("¿Qué significa \"Excuse me\"?", listOf("De nada", "Perdón / Disculpe", "Buenas noches", "Hasta luego"), 1),
                Question("Traduce: \"No entiendo\"", listOf("I understand", "I don't know", "I don't understand", "I can't speak"), 2),
                Question("¿Cómo se dice \"¿Cuánto cuesta?\"?", listOf("What time is it?", "Where is it?", "How old are you?", "How much is it?"), 3),
                Question("Traduce al inglés: \"Mucho gusto\"", type = "write", answer = "nice to meet you|pleased to meet you")
            )
        ),
        Lesson(
            id = "verbos2",
            title = "Verbos 2",
            description = "Más acciones comunes",
            questions = listOf(
                Question("¿Qué significa \"To run\"?", listOf("Caminar", "Correr", "Saltar", "Nadar"), 1),
                Question("¿Cómo se dice \"Dormir\"?", listOf("To eat", "To drink", "To sleep", "To work"), 2),
                Question("Traduce: \"She writes a letter\"", listOf("Ella lee una carta", "Ella escribe una carta", "Ella envía una carta", "Ella borra una carta"), 1),
                Question("¿Qué significa \"To speak\"?", listOf("Hablar", "Escuchar", "Ver", "Pensar"), 0),
                Question("Traduce al inglés: \"Yo hablo español\"", type = "write", answer = "i speak spanish|im speak spanish")
            )
        ),
        Lesson(
            id = "adjetivos",
            title = "Adjetivos",
            description = "Describir cosas y personas",
            questions = listOf(
                Question("¿Qué significa \"Big\"?", listOf("Pequeño", "Grande", "Rápido", "Feliz"), 1),
                Question("¿Cómo se dice \"Feliz\"?", listOf("Sad", "Angry", "Happy", "Tired"), 2),
                Question("El opuesto de \"Small\" es...", listOf("Little", "Short", "Big", "Tiny"), 2),
                Question("¿Qué significa \"Fast\"?", listOf("Lento", "Rápido", "Fuerte", "Frío"), 1),
                Question("Traduce al inglés: \"El perro es grande\"", type = "write", answer = "the dog is big|the dog is large")
            )
        ),
        Lesson(
            id = "tiempo",
            title = "El tiempo",
            description = "Clima y estaciones",
            questions = listOf(
                Question("¿Qué significa \"Rain\"?", listOf("Sol", "Lluvia", "Viento", "Nieve"), 1),
                Question("¿Cómo se dice \"Frío\"?", listOf("Hot", "Warm", "Cold", "Cool"), 2),
                Question("Traduce: \"The sun is hot\"", listOf("La luna es caliente", "El sol es caliente", "El sol está alto", "La arena es caliente"), 1),
                Question("¿Qué significa \"Snow\"?", listOf("Lluvia", "Tormenta", "Nieve", "Niebla"), 2),
                Question("Traduce al inglés: \"Hace calor\"", type = "write", answer = "it is hot|its hot")
            )
        ),
        Lesson(
            id = "ropa",
            title = "Ropa",
            description = "Prendas de vestir",
            questions = listOf(
                Question("¿Cómo se dice \"Camisa\"?", listOf("Shoes", "Shirt", "Skirt", "Socks"), 1),
                Question("¿Qué significa \"Shoes\"?", listOf("Sombrero", "Zapatos", "Pantalones", "Chaqueta"), 1),
                Question("Traduce: \"I need new shoes\"", listOf("Necesito zapatos nuevos", "Quiero una camisa nueva", "Necesito una bufanda", "Compré zapatos nuevos"), 0),
                Question("¿Cómo se dice \"Sombrero\"?", listOf("Hat", "Glove", "Belt", "Scarf"), 0),
                Question("Traduce al inglés: \"Tengo un sombrero azul\"", type = "write", answer = "i have a blue hat|ive got a blue hat")
            )
        )
    )
}
