package com.example.queryapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.queryapp.impl.QuizRepository

import com.example.queryapp.navigation.ScreenHolder
import com.example.queryapp.pages.AboutTeam

import com.example.queryapp.worker.NotificationMessage


// Navigation implementation based on "Navigation Basics in Jetpack Compose" by Stevdza_San
// https://www.youtube.com/watch?v=glyqjzkc4fk

@Composable
fun setUpNavGraph(
    navController: NavHostController,
    qr: QuizRepository = viewModel()
){


    NavHost(navController = navController, startDestination = ScreenHolder.Notification.route){

        composable(
            route = ScreenHolder.Notification.route
        ) {
            NotificationMessage(navController)
        }


        composable(
            route = ScreenHolder.Landing.route
        ) {
            Landing(navController, qr)
        }

        composable(
            route = ScreenHolder.SubjectSelection.route
        ) {
            SubjectSelection(navController, qr)
        }

        composable(
            route = ScreenHolder.Quiz.route
        ) {
            Quiz(navController, qr )
        }

        composable(
            route = ScreenHolder.QuizEnd.route
        ) {
            QuizEnd(navController, qr)
        }

        composable(
            route = ScreenHolder.AboutQuery.route
        ) {
           AboutQuery(navController)
        }

        composable(
            route = ScreenHolder.AboutTeam.route
        ) {
            AboutTeam(navController)
        }

    }
}