package com.example.queryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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

                    QueryappBeginnerTheme {

                        navController = rememberNavController()
                        setUpNavGraph(navController = navController)

                    }

        }

    }

    @Composable
    fun MainContent(qr: QuizRepository){
//        if (qr.isBeginnerDifficultyClicked()){
//            setContent{
//                QueryappBeginnerTheme {
//
//                    navController = rememberNavController()
//                    setUpNavGraph(navController = navController)
//
//                }
//            }
//
//        }
//
//       else if (qr.isIntermediateDifficultyClicked()){
//           setContent{
//               QueryappIntermediateTheme {
//
//                   navController = rememberNavController()
//                   setUpNavGraph(navController = navController)
//
//               }
//           }
//
//        }
//
//        else if (qr.isAdvancedDifficultyClicked()){
//            setContent{
//                QueryappAdvancedTheme {
//
//                    navController = rememberNavController()
//                    setUpNavGraph(navController = navController)
//
//                }
//            }
//
//        }



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