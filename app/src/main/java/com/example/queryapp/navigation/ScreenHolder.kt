package com.example.queryapp.navigation


sealed class ScreenHolder(val route: String) {
    object Notification: ScreenHolder(route = "notification_screen")
    object Landing: ScreenHolder(route = "landing_screen")
    object SubjectSelection: ScreenHolder(route = "subject_selection_screen")
    object Quiz: ScreenHolder(route = "quiz_screen")
    object QuizEnd: ScreenHolder(route = "quizend_screen")
    object AboutQuery: ScreenHolder(route = "about_query")
    object AboutTeam: ScreenHolder(route = "about_team")
}
