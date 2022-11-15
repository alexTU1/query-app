package com.example.queryapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.impl.QuizRepository
import com.example.queryapp.navigation.ScreenHolder
import kotlinx.coroutines.launch


@Composable
fun Landing(navController: NavController?, qr: QuizRepository) {
    val scope = rememberCoroutineScope()
    val state = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    Scaffold(
        scaffoldState = state,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch{
                                        state.drawerState.open()
                                    }
                                }){
                                    Icon(Icons.Rounded.Menu, "")
                                }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.height(50.dp),
                elevation = 0.dp
            )
        }
    ) {
        LandingContent(navController, qr)
    }
}

@Composable
fun LandingContent(navController: NavController?, qr: QuizRepository){
    Column(
        modifier = Modifier
            .background(colorResource(R.color.medium_purple))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.welcome),
            fontSize = 42.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 80.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetStarted(navController, qr)
    }
}

@Composable
fun GetStarted(navController: NavController?, qr: QuizRepository){
    Box(
        modifier = Modifier
            .background(colorResource(R.color.light_lavendar))
            .clip(RoundedCornerShape(20.dp))
            .height(900.dp)

    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom

        ) {
            Text(
                text = stringResource(R.string.get_started),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                //fontFamily = FontFamily.Serif,
                color = Color.Gray
            )
            Spacer(modifier =  Modifier.height(10.dp))
            Button(
                onClick = {
                    navController?.navigate(route = ScreenHolder.SubjectSelection.route)
                    qr.isBeginnerDifficultyClicked()
                    //logcat check
                    Log.d("Beginner: ", qr.isBeginnerDifficultyClicked().toString())
                    Log.d("Intermediate: ", qr.isIntermediateDifficultyClicked().toString())
                    Log.d("Advanced: ", qr.isAdvancedDifficultyClicked().toString())
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.light_purple),
                                colorResource(R.color.dark_purple)
                            )
                        )
                    )
            )
            {
                Text(
                    text = stringResource(R.string.beginner),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    //fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    navController?.navigate(route = ScreenHolder.SubjectSelection.route)
                    qr.isIntermediateDifficultyClicked()
                    //logcat check
                    Log.d("Beginner: ", qr.isBeginnerDifficultyClicked().toString())
                    Log.d("Intermediate: ", qr.isIntermediateDifficultyClicked().toString())
                    Log.d("Advanced: ", qr.isAdvancedDifficultyClicked().toString())
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.light_blue),
                                colorResource(R.color.dark_cyan)
                            )
                        )
                    )
            ) {
                Text(
                    text = stringResource(R.string.intermediate),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    //fontFamily = FontFamily.Serif,
                    color = Color.White)
            }
            Button(
                onClick = {
                    navController?.navigate(route = ScreenHolder.SubjectSelection.route)
                    qr.isAdvancedDifficultyClicked()
                    //logcat check
                    Log.d("Beginner: ", qr.isBeginnerDifficultyClicked().toString())
                    Log.d("Intermediate: ", qr.isIntermediateDifficultyClicked().toString())
                    Log.d("Advanced: ", qr.isAdvancedDifficultyClicked().toString())
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.light_peach),
                                colorResource(R.color.medium_peach),
                            )
                        )
                    )
            ) {
                Text(
                    text = stringResource(R.string.advanced),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                   // fontFamily = FontFamily.Serif,
                    color = Color.White)
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun LandingPreview(){
    Landing(navController = null, qr = viewModel())
}