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

data class SongLine(val english: String, val spanish: String)

data class Song(
    val id: String,
    val title: String,
    val lines: List<SongLine>
)

object SongRepository {
    val songs: List<Song> = listOf(
        Song(
            id = "twinkle",
            title = "Twinkle, Twinkle, Little Star",
            lines = listOf(
                SongLine("Twinkle, twinkle, little star", "Brilla, brilla, estrellita"),
                SongLine("How I wonder what you are", "Me pregunto qué serás"),
                SongLine("Up above the world so high", "Tan arriba en el cielo"),
                SongLine("Like a diamond in the sky", "Como un diamante en el cielo"),
                SongLine("Twinkle, twinkle, little star", "Brilla, brilla, estrellita"),
                SongLine("How I wonder what you are", "Me pregunto qué serás")
            )
        ),
        Song(
            id = "birthday",
            title = "Happy Birthday",
            lines = listOf(
                SongLine("Happy birthday to you", "Feliz cumpleaños a ti"),
                SongLine("Happy birthday to you", "Feliz cumpleaños a ti"),
                SongLine("Happy birthday, dear friend", "Feliz cumpleaños, querido amigo"),
                SongLine("Happy birthday to you", "Feliz cumpleaños a ti")
            )
        ),
        Song(
            id = "rowboat",
            title = "Row, Row, Row Your Boat",
            lines = listOf(
                SongLine("Row, row, row your boat", "Rema, rema, rema tu bote"),
                SongLine("Gently down the stream", "Suavemente por la corriente"),
                SongLine("Merrily, merrily, merrily, merrily", "Alegremente, alegremente"),
                SongLine("Life is but a dream", "La vida es solo un sueño")
            )
        ),
        Song(
            id = "macdonald",
            title = "Old MacDonald Had a Farm",
            lines = listOf(
                SongLine("Old MacDonald had a farm", "El viejo MacDonald tenía una granja"),
                SongLine("E-I-E-I-O", "E-I-E-I-O"),
                SongLine("And on his farm he had a cow", "Y en su granja tenía una vaca"),
                SongLine("E-I-E-I-O", "E-I-E-I-O"),
                SongLine("With a moo-moo here and a moo-moo there", "Con un muu aquí y un muu allá"),
                SongLine("Here a moo, there a moo, everywhere a moo-moo", "Aquí un muu, allá un muu, por todas partes muu-muu"),
                SongLine("Old MacDonald had a farm, E-I-E-I-O", "El viejo MacDonald tenía una granja, E-I-E-I-O")
            )
        ),
        Song(
            id = "happy",
            title = "If You're Happy and You Know It",
            lines = listOf(
                SongLine("If you're happy and you know it, clap your hands", "Si estás feliz y lo sabes, aplaude así"),
                SongLine("If you're happy and you know it, clap your hands", "Si estás feliz y lo sabes, aplaude así"),
                SongLine("If you're happy and you know it, and you really want to show it", "Si estás feliz y lo sabes, y de verdad lo quieres mostrar"),
                SongLine("If you're happy and you know it, clap your hands", "Si estás feliz y lo sabes, aplaude así")
            )
        ),
        Song(
            id = "wheels",
            title = "The Wheels on the Bus",
            lines = listOf(
                SongLine("The wheels on the bus go round and round", "Las ruedas del autobús giran y giran"),
                SongLine("Round and round, round and round", "Giran y giran, giran y giran"),
                SongLine("The wheels on the bus go round and round", "Las ruedas del autobús giran y giran"),
                SongLine("All through the town", "Por todo el pueblo")
            )
        )
    )
}

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
        ),
        Lesson(
            id = "animales",
            title = "Animales",
            description = "Animales domésticos y salvajes",
            questions = listOf(
                Question("¿Qué significa \"Dog\"?", listOf("Gato", "Perro", "Pájaro", "Pez"), 1),
                Question("¿Cómo se dice \"Caballo\"?", listOf("Cow", "Pig", "Horse", "Sheep"), 2),
                Question("Traduce: \"The bird sings\"", listOf("El pájaro vuela", "El pájaro canta", "El pájaro come", "El pájaro duerme"), 1),
                Question("¿Qué significa \"Cat\"?", listOf("Perro", "Caballo", "Gato", "Conejo"), 2),
                Question("Traduce al inglés: \"Tengo un perro y un gato\"", type = "write", answer = "i have a dog and a cat|ive got a dog and a cat")
            )
        ),
        Lesson(
            id = "cuerpo",
            title = "Cuerpo y salud",
            description = "Partes del cuerpo",
            questions = listOf(
                Question("¿Qué significa \"Head\"?", listOf("Mano", "Cabeza", "Pie", "Rodilla"), 1),
                Question("¿Cómo se dice \"Mano\"?", listOf("Foot", "Arm", "Hand", "Finger"), 2),
                Question("Traduce: \"Me duele la cabeza\"", listOf("My hand hurts", "My head hurts", "My feet hurt", "My head is big"), 1),
                Question("¿Qué significa \"Eye\"?", listOf("Ojo", "Oído", "Boca", "Nariz"), 0),
                Question("Traduce al inglés: \"Necesito un doctor\"", type = "write", answer = "i need a doctor")
            )
        ),
        Lesson(
            id = "ciudad",
            title = "Ciudad y lugares",
            description = "Lugares de la ciudad",
            questions = listOf(
                Question("¿Qué significa \"Market\"?", listOf("Escuela", "Mercado", "Calle", "Parque"), 1),
                Question("¿Cómo se dice \"Hospital\"?", listOf("Colegio", "Mercado", "Hospital", "Banco"), 2),
                Question("Traduce: \"Where is the park?\"", listOf("¿Dónde está el parque?", "¿Dónde está el mercado?", "¿Cuándo es el parque?", "¿Qué es el parque?"), 0),
                Question("¿Qué significa \"Street\"?", listOf("Avenida", "Calle", "Plaza", "Puente"), 1),
                Question("Traduce al inglés: \"Voy al mercado\"", type = "write", answer = "i go to the market|im going to the market")
            )
        ),
        Lesson(
            id = "casa",
            title = "Casa",
            description = "Habitaciones y objetos",
            questions = listOf(
                Question("¿Qué significa \"Kitchen\"?", listOf("Sala", "Cocina", "Baño", "Dormitorio"), 1),
                Question("¿Cómo se dice \"Ventana\"?", listOf("Door", "Window", "Chair", "Table"), 1),
                Question("Traduce: \"La puerta está abierta\"", listOf("The door is open", "The window is open", "The door is closed", "The box is open"), 0),
                Question("¿Qué significa \"Bed\"?", listOf("Mesa", "Silla", "Cama", "Armario"), 2),
                Question("Traduce al inglés: \"Mi casa es pequeña\"", type = "write", answer = "my house is small|my home is small")
            )
        ),
        Lesson(
            id = "rutina",
            title = "Rutina diaria",
            description = "Acciones de cada día",
            questions = listOf(
                Question("¿Qué significa \"Wake up\"?", listOf("Dormirse", "Despertarse", "Ducharse", "Comer"), 1),
                Question("¿Cómo se dice \"Desayunar\"?", listOf("To eat", "To have breakfast", "To lunch", "To dine"), 1),
                Question("Traduce: \"I go to bed at ten\"", listOf("Me acuesto a las diez", "Voy a la cama nueva", "Duermo diez horas", "Me levanto a las diez"), 0),
                Question("¿Qué significa \"Work\"?", listOf("Jugar", "Trabajar", "Estudiar", "Descansar"), 1),
                Question("Traduce al inglés: \"Me despierto temprano\"", type = "write", answer = "i wake up early|i get up early")
            )
        ),
        Lesson(
            id = "trabajo",
            title = "Trabajo y escuela",
            description = "Estudio y oficios",
            questions = listOf(
                Question("¿Qué significa \"Teacher\"?", listOf("Estudiante", "Profesor(a)", "Doctor", "Policía"), 1),
                Question("¿Cómo se dice \"Libro\"?", listOf("Book", "Pen", "Bag", "Paper"), 0),
                Question("Traduce: \"I study every day\"", listOf("Estudio todos los días", "Trabajo todos los días", "Leo cada día", "Estudio por la mañana"), 0),
                Question("¿Qué significa \"Office\"?", listOf("Fábrica", "Tienda", "Oficina", "Campo"), 2),
                Question("Traduce al inglés: \"Yo trabajo mucho\"", type = "write", answer = "i work a lot|i work very much")
            )
        ),
        Lesson(
            id = "deportes",
            title = "Deportes y pasatiempos",
            description = "Hobbies y diversión",
            questions = listOf(
                Question("¿Qué significa \"Soccer\"?", listOf("Baloncesto", "Fútbol", "Tenis", "Béisbol"), 1),
                Question("¿Cómo se dice \"Nadar\"?", listOf("To swim", "To run", "To climb", "To dance"), 0),
                Question("Traduce: \"I like to play guitar\"", listOf("Me gusta tocar la guitarra", "Me gusta escuchar guitarra", "Me gusta la guitarra nueva", "Quiero una guitarra"), 0),
                Question("¿Qué significa \"To read\"?", listOf("Escribir", "Leer", "Dibujar", "Cantar"), 1),
                Question("Traduce al inglés: \"Me gusta el fútbol\"", type = "write", answer = "i like soccer|i like football")
            )
        ),
        Lesson(
            id = "fechas",
            title = "Días y fechas",
            description = "Días, meses y horas del día",
            questions = listOf(
                Question("¿Qué significa \"Monday\"?", listOf("Domingo", "Lunes", "Martes", "Miércoles"), 1),
                Question("¿Qué significa \"Month\"?", listOf("Día", "Semana", "Mes", "Año"), 2),
                Question("Traduce: \"Today is Friday\"", listOf("Hoy es viernes", "Mañana es viernes", "Hoy es lunes", "Ayer fue viernes"), 0),
                Question("¿Cómo se dice \"Ayer\"?", listOf("Today", "Tomorrow", "Yesterday", "Now"), 2),
                Question("Traduce al inglés: \"Hoy es lunes\"", type = "write", answer = "today is monday|it is monday today|today its monday")
            )
        ),
        Lesson(
            id = "compras",
            title = "Compras y dinero",
            description = "En la tienda",
            questions = listOf(
                Question("¿Qué significa \"Money\"?", listOf("Tienda", "Dinero", "Precio", "Cuenta"), 1),
                Question("¿Cómo se dice \"Caro\"?", listOf("Cheap", "Expensive", "Free", "New"), 1),
                Question("Traduce: \"Can I pay by card?\"", listOf("¿Puedo pagar con tarjeta?", "¿Puedo pagar en efectivo?", "¿Tienen tarjeta?", "¿Dónde pago?"), 0),
                Question("¿Qué significa \"To buy\"?", listOf("Vender", "Comprar", "Pagar", "Preguntar"), 1),
                Question("Traduce al inglés: \"¿Cuánto cuesta esto?\"", type = "write", answer = "how much is this|how much does this cost|how much does it cost")
            )
        ),
        Lesson(
            id = "pasado",
            title = "Pasado simple",
            description = "Verbos en pasado (avanzado)",
            questions = listOf(
                Question("¿Cuál es el pasado de \"eat\"?", listOf("Eated", "Ate", "Eats", "Eaten"), 1),
                Question("¿Cuál es el pasado de \"see\"?", listOf("Seed", "Saw", "Seen", "See"), 1),
                Question("Traduce: \"Ayer fui al cine\"", listOf("I went to the cinema", "I go to the cinema", "I will go to the cinema", "I was the cinema"), 0),
                Question("¿Cuál es el pasado de \"do\"?", listOf("Does", "Did", "Done", "Doing"), 1),
                Question("Traduce al inglés: \"Ayer comí pizza\"", type = "write", answer = "i ate pizza yesterday|yesterday i ate pizza")
            )
        ),
        Lesson(
            id = "futuro",
            title = "Futuro",
            description = "Will y going to (avanzado)",
            questions = listOf(
                Question("Traduce: \"I will travel tomorrow\"", listOf("Viajaré mañana", "Viajé ayer", "Viajo mañana", "Viajaba mañana"), 0),
                Question("Completa: She ___ go to school tomorrow.", listOf("will", "was", "is", "did"), 0),
                Question("¿Qué significa \"going to\"?", listOf("Pasado", "Ir a (futuro planeado)", "Gerundio", "Condicional"), 1),
                Question("Traduce: \"Will you come?\"", listOf("¿Vendrás?", "¿Viniste?", "¿Vienes siempre?", "¿De dónde vienes?"), 0),
                Question("Traduce al inglés: \"Mañana lloverá\"", type = "write", answer = "it will rain tomorrow|tomorrow it will rain")
            )
        ),
        Lesson(
            id = "comparativos",
            title = "Comparativos",
            description = "Más... que (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Bigger\"?", listOf("Más grande", "El más grande", "Más pequeño", "Tan grande como"), 0),
                Question("¿Cuál es el comparativo de \"good\"?", listOf("Gooder", "Better", "More good", "Best"), 1),
                Question("Traduce: \"She is taller than me\"", listOf("Ella es más alta que yo", "Ella es tan alta como yo", "Ella es la más alta", "Yo soy más alto"), 0),
                Question("\"Worse\" es el comparativo de...", listOf("Bad", "Good", "Small", "Easy"), 0),
                Question("Traduce al inglés: \"Este libro es mejor\"", type = "write", answer = "this book is better|this book is the better")
            )
        ),
        Lesson(
            id = "preposiciones",
            title = "Preposiciones",
            description = "In, on, at, under (avanzado)",
            questions = listOf(
                Question("Completa: The book is ___ the table.", listOf("on", "in", "at", "of"), 0),
                Question("¿Qué significa \"under\"?", listOf("Sobre", "Debajo de", "Dentro de", "Entre"), 1),
                Question("Completa: I live ___ Mexico.", listOf("in", "on", "at", "to"), 0),
                Question("Traduce: \"El gato está debajo de la mesa\"", listOf("The cat is under the table", "The cat is on the table", "The cat is in the table", "The table is under the cat"), 0),
                Question("Traduce al inglés: \"Estoy en casa\"", type = "write", answer = "i am at home|im at home|i am home")
            )
        ),
        Lesson(
            id = "preguntas",
            title = "Palabras de pregunta",
            description = "What, who, where, why (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Why\"?", listOf("Quién", "Por qué", "Cuándo", "Dónde"), 1),
                Question("¿Qué palabra se usa para preguntar por una persona?", listOf("What", "Who", "When", "How"), 1),
                Question("Traduce: \"Why are you sad?\"", listOf("¿Por qué estás triste?", "¿Qué estás triste?", "¿Cuándo estás triste?", "¿Dónde estás triste?"), 0),
                Question("¿Qué significa \"How many\"?", listOf("¿Cómo?", "¿Cuántos? (cosas contables)", "¿Qué tan?", "¿Quiénes?"), 1),
                Question("Traduce al inglés: \"¿Dónde vives?\"", type = "write", answer = "where do you live|where do you live?")
            )
        ),
        Lesson(
            id = "conectores",
            title = "Conectores",
            description = "And, but, because, or (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"because\"?", listOf("Pero", "Porque", "Y", "O"), 1),
                Question("Completa: I like tea ___ not coffee.", listOf("but", "and", "or", "so"), 0),
                Question("Traduce: \"Estudio porque quiero aprender\"", listOf("I study because I want to learn", "I study and I want to learn", "I study but I want to learn", "I want to learn but I study"), 0),
                Question("¿Qué significa \"or\"?", listOf("Y", "O", "Pero", "Porque"), 1),
                Question("Traduce al inglés: \"Yo trabajo y estudio\"", type = "write", answer = "i work and study|i work and i study")
            )
        ),
        Lesson(
            id = "viajes",
            title = "Viajes",
            description = "Aeropuerto y vuelos (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Flight\"?", listOf("Tren", "Vuelo", "Equipaje", "Pasaporte"), 1),
                Question("¿Cómo se dice \"Equipaje\"?", listOf("Ticket", "Luggage", "Passport", "Seat"), 1),
                Question("Traduce: \"Where is the airport?\"", listOf("¿Dónde está el aeropuerto?", "¿Dónde está el avión?", "¿Cuándo sale el avión?", "¿Dónde está el avión nuevo?"), 0),
                Question("¿Qué significa \"Ticket\"?", listOf("Boleto", "Pasaporte", "Maleta", "Asiento"), 0),
                Question("Traduce al inglés: \"Necesito un boleto\"", type = "write", answer = "i need a ticket")
            )
        ),
        Lesson(
            id = "restaurante",
            title = "Restaurante",
            description = "Pedir comida (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Menu\"?", listOf("Cuenta", "Carta / menú", "Mesero", "Comida"), 1),
                Question("¿Cómo se dice \"La cuenta, por favor\"?", listOf("The menu, please", "The bill, please", "The waiter, please", "The food, please"), 1),
                Question("Traduce: \"I would like water\"", listOf("Quiero agua", "Me gusta el agua", "Bebo agua", "Necesito una botella"), 0),
                Question("¿Qué significa \"Waiter\"?", listOf("Mesero", "Cocinero", "Cliente", "Jefe"), 0),
                Question("Traduce al inglés: \"La cuenta, por favor\"", type = "write", answer = "the bill please|the check please|can i have the bill")
            )
        ),
        Lesson(
            id = "hora",
            title = "La hora",
            description = "Decir la hora (avanzado)",
            questions = listOf(
                Question("Traduce: \"It is three o'clock\"", listOf("Son las tres", "Son tres horas", "Tres veces", "Es el tercero"), 0),
                Question("¿Qué significa \"Half past six\"?", listOf("Seis y media", "Seis y cuarto", "Siete y media", "Seis en punto"), 0),
                Question("¿Qué significa \"a.m.\"?", listOf("Antes del mediodía", "Después del mediodía", "En la noche", "Medianoche"), 0),
                Question("¿Qué significa \"Midnight\"?", listOf("Medianoche", "Mediodía", "Mañana", "Anoche"), 0),
                Question("Traduce al inglés: \"Son las ocho\"", type = "write", answer = "it is eight|its eight|it is eight oclock|its eight oclock")
            )
        ),
        Lesson(
            id = "familia2",
            title = "Familia 2",
            description = "Más miembros de la familia (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Grandmother\"?", listOf("Abuela", "Abuelo", "Esposa", "Prima"), 0),
                Question("¿Cómo se dice \"Esposo\"?", listOf("Wife", "Husband", "Uncle", "Child"), 1),
                Question("Traduce: \"My parents live here\"", listOf("Mis padres viven aquí", "Mis amigos viven aquí", "Mis padres trabajan aquí", "Mis primos viven aquí"), 0),
                Question("¿Qué significa \"Children\"?", listOf("Padres", "Hijos", "Hermanos", "Sobrinos"), 1),
                Question("Traduce al inglés: \"Mi abuelo es mayor\"", type = "write", answer = "my grandfather is old|my grandpa is old")
            )
        ),
        Lesson(
            id = "emociones",
            title = "Emociones",
            description = "Sentimientos y estados de ánimo (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Angry\"?", listOf("Enojado", "Feliz", "Cansado", "Asustado"), 0),
                Question("¿Cómo se dice \"Cansado\"?", listOf("Tired", "Hungry", "Thirsty", "Sleepy"), 0),
                Question("Traduce: \"I am happy today\"", listOf("Estoy feliz hoy", "Estoy triste hoy", "Estoy cansado hoy", "Estaba feliz hoy"), 0),
                Question("¿Qué significa \"Scared\"?", listOf("Asustado", "Sorprendido", "Triste", "Aburrido"), 0),
                Question("Traduce al inglés: \"Estoy triste\"", type = "write", answer = "i am sad|im sad")
            )
        ),
        Lesson(
            id = "naturaleza",
            title = "Naturaleza",
            description = "Plantas y paisajes (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Tree\"?", listOf("Flor", "Árbol", "Río", "Hoja"), 1),
                Question("¿Cómo se dice \"Montaña\"?", listOf("Mountain", "River", "Beach", "Lake"), 0),
                Question("Traduce: \"The river is long\"", listOf("El río es largo", "El río es corto", "El río es ancho", "La montaña es larga"), 0),
                Question("¿Qué significa \"Flower\"?", listOf("Árbol", "Hoja", "Flor", "Planta"), 2),
                Question("Traduce al inglés: \"El árbol es verde\"", type = "write", answer = "the tree is green")
            )
        ),
        Lesson(
            id = "tecnologia",
            title = "Tecnología",
            description = "Teléfonos y computadoras (avanzado)",
            questions = listOf(
                Question("¿Cómo se dice \"Teléfono\"?", listOf("Phone", "Computer", "Keyboard", "Screen"), 0),
                Question("¿Qué significa \"Screen\"?", listOf("Teclado", "Pantalla", "Ratón", "Cable"), 1),
                Question("Traduce: \"I send an email\"", listOf("Envío un correo", "Recibo un correo", "Borro un correo", "Leo un correo"), 0),
                Question("¿Qué significa \"Charger\"?", listOf("Cargador", "Audífonos", "Batería", "Cable"), 0),
                Question("Traduce al inglés: \"Mi teléfono es nuevo\"", type = "write", answer = "my phone is new|my telephone is new")
            )
        ),
        Lesson(
            id = "medico",
            title = "En el médico",
            description = "Salud y síntomas (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Medicine\"?", listOf("Doctor", "Medicina", "Dolor", "Fiebre"), 1),
                Question("¿Cómo se dice \"Fiebre\"?", listOf("Cough", "Fever", "Pain", "Cold"), 1),
                Question("Traduce: \"Tengo dolor de cabeza\"", listOf("I have a headache", "I have a stomachache", "I have a cold", "I have a fever"), 0),
                Question("¿Qué significa \"Pill\"?", listOf("Pastilla", "Jarabe", "Inyección", "Vendaje"), 0),
                Question("Traduce al inglés: \"Tengo fiebre\"", type = "write", answer = "i have a fever|i have fever")
            )
        ),
        Lesson(
            id = "celebraciones",
            title = "Celebraciones",
            description = "Fiestas y cumpleaños (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Birthday\"?", listOf("Cumpleaños", "Boda", "Fiesta", "Regalo"), 0),
                Question("¿Cómo se dice \"Pastel\"?", listOf("Cake", "Candy", "Balloon", "Card"), 0),
                Question("Traduce: \"Happy birthday to you\"", listOf("Feliz cumpleaños", "Feliz año nuevo", "Felicidades por todo", "Buen provecho"), 0),
                Question("¿Qué significa \"Gift\"?", listOf("Regalo", "Globos", "Invitados", "Música"), 0),
                Question("Traduce al inglés: \"Feliz cumpleaños\"", type = "write", answer = "happy birthday|happy birthday to you")
            )
        ),
        Lesson(
            id = "frutas",
            title = "Frutas y verduras",
            description = "En el mercado",
            questions = listOf(
                Question("¿Qué significa \"Strawberry\"?", listOf("Fresa", "Melón", "Uva", "Durazno"), 0),
                Question("¿Cómo se dice \"Cebolla\"?", listOf("Onion", "Garlic", "Carrot", "Pepper"), 0),
                Question("Traduce: \"I eat vegetables every day\"", listOf("Como verduras todos los días", "Como frutas todos los días", "Compro verduras todos los días", "Como verduras hoy"), 0),
                Question("¿Qué significa \"Lemon\"?", listOf("Limón", "Lima", "Mango", "Piña"), 0),
                Question("Traduce al inglés: \"Me gusta la manzana\"", type = "write", answer = "i like apples|i like the apple|i like apple")
            )
        ),
        Lesson(
            id = "transporte",
            title = "Transporte",
            description = "Cómo viajar",
            questions = listOf(
                Question("¿Qué significa \"Bus\"?", listOf("Bus", "Tren", "Bicicleta", "Camión de carga"), 0),
                Question("¿Cómo se dice \"Tren\"?", listOf("Train", "Truck", "Tram", "Subway"), 0),
                Question("Traduce: \"I go to work by bus\"", listOf("Voy al trabajo en autobús", "Voy al trabajo a pie", "Voy al trabajo en tren", "Trabajo en el autobús"), 0),
                Question("¿Qué significa \"Bicycle\"?", listOf("Motocicleta", "Bicicleta", "Patines", "Carro"), 1),
                Question("Traduce al inglés: \"Voy en coche\"", type = "write", answer = "i go by car|i travel by car")
            )
        ),
        Lesson(
            id = "direcciones",
            title = "Direcciones",
            description = "Pedir y dar indicaciones",
            questions = listOf(
                Question("¿Qué significa \"Left\"?", listOf("Izquierda", "Derecha", "Recto", "Detrás"), 0),
                Question("¿Cómo se dice \"Recto\"?", listOf("Straight", "Stop", "Back", "Side"), 0),
                Question("Traduce: \"Turn right at the corner\"", listOf("Gira a la derecha en la esquina", "Gira a la izquierda en la esquina", "Sigue recto en la esquina", "Gira a la derecha cerca"), 0),
                Question("¿Qué significa \"Near\"?", listOf("Cerca", "Lejos", "Entre", "Encima"), 0),
                Question("Traduce al inglés: \"Gira a la izquierda\"", type = "write", answer = "turn left|turn to the left")
            )
        ),
        Lesson(
            id = "profesiones",
            title = "Profesiones",
            description = "Oficios y carreras",
            questions = listOf(
                Question("¿Qué significa \"Nurse\"?", listOf("Enfermero(a)", "Médico", "Policía", "Maestro"), 0),
                Question("¿Cómo se dice \"Bombero\"?", listOf("Firefighter", "Fisherman", "Farmer", "Salesman"), 0),
                Question("Traduce: \"She is an engineer\"", listOf("Ella es ingeniera", "Él es ingeniero", "Ella es profesora", "Ella es arquitecta"), 0),
                Question("¿Qué significa \"Lawyer\"?", listOf("Abogado", "Contador", "Panadero", "Mecánico"), 0),
                Question("Traduce al inglés: \"Soy maestro\"", type = "write", answer = "i am a teacher|im a teacher|i am teacher")
            )
        ),
        Lesson(
            id = "formas",
            title = "Formas y tamaños",
            description = "Figuras geométricas",
            questions = listOf(
                Question("¿Qué significa \"Circle\"?", listOf("Círculo", "Cuadrado", "Triángulo", "Línea"), 0),
                Question("¿Cómo se dice \"Cuadrado\"?", listOf("Square", "Triangle", "Star", "Heart"), 0),
                Question("Traduce: \"The box is square\"", listOf("La caja es cuadrada", "La caja es redonda", "La caja es grande", "El cuadrado es de caja"), 0),
                Question("¿Qué significa \"Heart\"?", listOf("Corazón", "Estrella", "Media luna", "Flecha"), 0),
                Question("Traduce al inglés: \"Es un círculo\"", type = "write", answer = "it is a circle|its a circle")
            )
        ),
        Lesson(
            id = "higiene",
            title = "Higiene y aseo",
            description = "Cuidado personal",
            questions = listOf(
                Question("¿Qué significa \"Toothbrush\"?", listOf("Cepillo de dientes", "Toalla", "Jabón", "Peine"), 0),
                Question("¿Cómo se dice \"Jabón\"?", listOf("Soap", "Shampoo", "Towel", "Sponge"), 0),
                Question("Traduce: \"I take a shower every morning\"", listOf("Me ducho cada mañana", "Me baño cada noche", "Me lavo cada mañana", "Me ducho en las mañanas de la semana"), 0),
                Question("¿Qué significa \"Mirror\"?", listOf("Espejo", "Lavabo", "Toalla", "Tijeras"), 0),
                Question("Traduce al inglés: \"Lavo mis manos\"", type = "write", answer = "i wash my hands")
            )
        ),
        Lesson(
            id = "cortesia",
            title = "Cortesía",
            description = "Frases educadas (avanzado)",
            questions = listOf(
                Question("¿Cómo se dice \"¿Me ayudas, por favor?\"?", listOf("Can you help me, please?", "Do you help me?", "You can help me?", "Help me now?"), 0),
                Question("¿Qué significa \"You're welcome\"?", listOf("Bienvenido", "De nada", "Por favor", "Buena suerte"), 1),
                Question("Traduce: \"Would you like some tea?\"", listOf("¿Quieres té?", "¿Te gusta el té?", "¿Hay té?", "¿Es té?"), 0),
                Question("¿Cómo se dice \"Con permiso\"?", listOf("Excuse me", "Sorry", "Please", "Thanks"), 0),
                Question("Traduce al inglés: \"Por favor\"", type = "write", answer = "please")
            )
        ),
        Lesson(
            id = "salvajes",
            title = "Animales salvajes",
            description = "Animales de la selva",
            questions = listOf(
                Question("¿Qué significa \"Lion\"?", listOf("León", "Tigre", "Oso", "Lobo"), 0),
                Question("¿Cómo se dice \"Tigre\"?", listOf("Lion", "Wolf", "Tiger", "Bear"), 2),
                Question("Traduce: \"The lion is the king\"", listOf("El león es el rey", "El tigre es el rey", "El león es fuerte", "El rey es un león"), 0),
                Question("¿Qué significa \"Monkey\"?", listOf("Mono", "Elefante", "Jirafa", "Cebra"), 0),
                Question("Traduce al inglés: \"El tigre es rápido\"", type = "write", answer = "the tiger is fast|the tiger is quick")
            )
        ),
        Lesson(
            id = "playa",
            title = "En la playa",
            description = "Vacaciones en la playa",
            questions = listOf(
                Question("¿Qué significa \"Sand\"?", listOf("Arena", "Olas", "Sombrilla", "Concha"), 0),
                Question("¿Cómo se dice \"Sombrilla\"?", listOf("Umbrella", "Sunscreen", "Towel", "Bucket"), 0),
                Question("Traduce: \"I swim in the sea\"", listOf("Nado en el mar", "Nado en la piscina", "Veo el mar", "Voy al mar"), 0),
                Question("¿Qué significa \"Wave\"?", listOf("Ola", "Arena", "Sol", "Barco"), 0),
                Question("Traduce al inglés: \"La playa es hermosa\"", type = "write", answer = "the beach is beautiful|the beach is pretty")
            )
        ),
        Lesson(
            id = "musica",
            title = "Música",
            description = "Instrumentos y canciones",
            questions = listOf(
                Question("¿Qué significa \"Song\"?", listOf("Canción", "Banda", "Disco", "Baile"), 0),
                Question("¿Cómo se dice \"Batería\" (instrumento)?", listOf("Drums", "Guitar", "Piano", "Flute"), 0),
                Question("Traduce: \"I listen to music\"", listOf("Escucho música", "Toco música", "Veo música", "Compro música"), 0),
                Question("¿Qué significa \"To sing\"?", listOf("Cantar", "Bailar", "Tocar", "Escuchar"), 0),
                Question("Traduce al inglés: \"Me gusta esta canción\"", type = "write", answer = "i like this song")
            )
        ),
        Lesson(
            id = "estaciones",
            title = "Estaciones del año",
            description = "Seasons (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Spring\"?", listOf("Primavera", "Verano", "Otoño", "Invierno"), 0),
                Question("¿Cómo se dice \"Verano\"?", listOf("Summer", "Winter", "Autumn", "Spring"), 0),
                Question("Traduce: \"In winter it is cold\"", listOf("En invierno hace frío", "En invierno hace calor", "En verano hace frío", "El invierno es corto"), 0),
                Question("¿Qué significa \"Autumn / Fall\"?", listOf("Otoño", "Primavera", "Noche", "Semana"), 0),
                Question("Traduce al inglés: \"El verano es caliente\"", type = "write", answer = "summer is hot|the summer is hot")
            )
        ),
        Lesson(
            id = "verbos3",
            title = "Verbos 3",
            description = "Verbos útiles del día a día (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"To get up\"?", listOf("Levantarse", "Acostarse", "Sentarse", "Levantar objetos"), 0),
                Question("¿Cómo se dice \"Olvidar\"?", listOf("To forget", "To remember", "To leave", "To lose"), 0),
                Question("Traduce: \"I always forget names\"", listOf("Siempre olvido los nombres", "Nunca olvido los nombres", "Siempre recuerdo los nombres", "Olvidé tu nombre"), 0),
                Question("¿Qué significa \"To try\"?", listOf("Intentar", "Tirar", "Ganar", "Perder"), 0),
                Question("Traduce al inglés: \"Recuerdo tu nombre\"", type = "write", answer = "i remember your name")
            )
        ),
        Lesson(
            id = "escuela2",
            title = "Escuela 2",
            description = "Útiles escolares (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"Backpack\"?", listOf("Mochila", "Pizarra", "Borrador", "Lápiz"), 0),
                Question("¿Cómo se dice \"Borrador\"?", listOf("Eraser", "Marker", "Notebook", "Ruler"), 0),
                Question("Traduce: \"The teacher writes on the board\"", listOf("El maestro escribe en el pizarrón", "El maestro borra el pizarrón", "El alumno escribe en el pizarrón", "El maestro lee el pizarrón"), 0),
                Question("¿Qué significa \"Ruler\"?", listOf("Regla", "Tijeras", "Grapas", "Pegamento"), 0),
                Question("Traduce al inglés: \"Abro mi mochila\"", type = "write", answer = "i open my backpack")
            )
        ),
        Lesson(
            id = "cocina",
            title = "En la cocina",
            description = "Verbos de cocinar (avanzado)",
            questions = listOf(
                Question("¿Qué significa \"To boil\"?", listOf("Hervir", "Freír", "Cortar", "Mezclar"), 0),
                Question("¿Cómo se dice \"Cuchillo\"?", listOf("Knife", "Fork", "Spoon", "Plate"), 0),
                Question("Traduce: \"Cut the onion\"", listOf("Corta la cebolla", "Pela la cebolla", "Compra la cebolla", "Corta el pan"), 0),
                Question("¿Qué significa \"To bake\"?", listOf("Hornear", "Picar", "Hervir", "Servir"), 0),
                Question("Traduce al inglés: \"Mezcla el agua y la sal\"", type = "write", answer = "mix the water and the salt|mix water and salt")
            )
        )
    )
}
