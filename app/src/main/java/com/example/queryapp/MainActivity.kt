package com.example.queryapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.example.queryapp.impl.QuizRepository
import com.example.queryapp.ui.theme.QueryappAdvancedTheme
import com.example.queryapp.ui.theme.QueryappBeginnerTheme
import com.example.queryapp.ui.theme.QueryappIntermediateTheme


import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.queryapp.impl.QuizRepository




import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState



class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{

            QueryappBeginnerTheme {
                navController = rememberNavController()
                setUpNavGraph(navController = navController)
            }
            val qr: QuizRepository = viewModel()
            ThemeChanger(qr = qr)
            RequestNotificationPermission()
        }
    }



@RequiresApi(33)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermission() {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS
    )
    if (!permissionState.status.isGranted) {
        LaunchedEffect(key1 = true) {
            permissionState.launchPermissionRequest()

        }
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



        QueryappBeginnerTheme {
            Greeting("me")
        }
        QueryappBeginnerTheme {}

    }
}