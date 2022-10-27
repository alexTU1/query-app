package com.example.queryapp

sealed class ScreenHolder(val route: String) {
    object Landing: ScreenHolder(route = "landing_screen")
    object Quiz: ScreenHolder(route = "quiz_screen")
    object QuizEnd: ScreenHolder(route = "quizend_screen")
}
