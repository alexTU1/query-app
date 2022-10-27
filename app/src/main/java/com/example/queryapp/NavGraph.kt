package com.example.queryapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Navigation implementation based on "Navigation Basics in Jetpack Compose" by Stevdza_San
// https://www.youtube.com/watch?v=glyqjzkc4fk

@Composable
fun setUpNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = ScreenHolder.Landing.route){
        composable(
            route = ScreenHolder.Landing.route
        ) {
            Landing(navController)
        }

        composable(
            route = ScreenHolder.Quiz.route
        ) {
            Quiz(navController)
        }

        composable(
            route = ScreenHolder.QuizEnd.route
        ) {
            QuizEnd(navController)
        }

    }
}