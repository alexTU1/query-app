package com.example.queryapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun About(navController: NavController?){
    Column() {
        Text(text = "About the team...")
    }
}