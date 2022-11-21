package com.example.queryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.queryapp.ui.theme.QueryappAdvancedTheme
import com.example.queryapp.ui.theme.QueryappBeginnerTheme
import com.example.queryapp.ui.theme.QueryappIntermediateTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.queryapp.impl.QuizRepository

//import com.example.queryapp.ui.theme.QueryappTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val qr: QuizRepository = viewModel()
            ThemeChanger(qr = qr)
            /*when(enum...) {
                Beginner -> {

    }
    }*/
//                    QueryappBeginnerTheme {
//                        navController = rememberNavController()
//                        setUpNavGraph(navController = navController)
//                    }



        }

    }


    @Composable
    fun ThemeChanger(qr: QuizRepository) {
        when(qr.themeType.value){
            ThemeType.BEGINNER -> {
                return QueryappBeginnerTheme {
                    navController = rememberNavController()
                    setUpNavGraph(navController = navController)
                }
            }
            ThemeType.INTERMEDIATE -> {
                return QueryappIntermediateTheme {
                    navController = rememberNavController()
                    setUpNavGraph(navController = navController)
                }
            }
            ThemeType.ADVANCED -> {
                return QueryappAdvancedTheme {
                    navController = rememberNavController()
                    setUpNavGraph(navController = navController)
                }
            }
        }
    }



    enum class ThemeType{
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name")
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {

        QueryappBeginnerTheme {
            Greeting("me")
        }

    }
}