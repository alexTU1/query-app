package com.example.queryapp

// Navigation implementation based on "Navigation Basics in Jetpack Compose" by Stevdza_San
// https://www.youtube.com/watch?v=glyqjzkc4fk

sealed class ScreenHolder(val route: String) {
    object Landing: ScreenHolder(route = "landing_screen")
    object SubjectSelection: ScreenHolder(route = "subject_selection_screen")
    object Quiz: ScreenHolder(route = "quiz_screen")
    object QuizEnd: ScreenHolder(route = "quizend_screen")
}
