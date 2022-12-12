package com.example.queryapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
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
import com.example.queryapp.pages.LandingPage.NavDrawerItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Landing(navController: NavController?, qr: QuizRepository) {
    val scope = rememberCoroutineScope()
    val state = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    Scaffold(
        scaffoldState = state,
        drawerBackgroundColor = MaterialTheme.colors.primary,
        drawerShape = RoundedCornerShape(0.dp, 40.dp, 40.dp, 0.dp),
        drawerContent = {
            NavDrawerHeader(state, scope)
            Spacer(modifier = Modifier.height(5.dp))
            Divider(
                startIndent = 0.dp,
                thickness = 2.dp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.blur(radius = 5.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            )
            NavDrawerContent(
                drawerItems = listOf(
                    NavDrawerItem(name = "Home", icon = Icons.Default.Home),
                    NavDrawerItem(name = "Subjects", icon = Icons.Default.Star),
                    NavDrawerItem(name = "About", icon = Icons.Default.Info)
                ), whenClicked = {
                    when(it.name){
                        "Home" -> navController?.navigate(route = ScreenHolder.Landing.route)
                        "Subjects" -> navController?.navigate(route = ScreenHolder.SubjectSelection.route)
                        "About" -> navController?.navigate(route = ScreenHolder.AboutQuery.route)
                    }
                }
            )
        },
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

@SuppressLint("LongLogTag")
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
                    Log.d("theme: ", qr.getTheme().toString())
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
                    qr.getTheme() === MainActivity.ThemeType.INTERMEDIATE


                    //logcat check
                    Log.d("Beginner: ", qr.isBeginnerDifficultyClicked().toString())
                    Log.d("Intermediate: ", qr.isIntermediateDifficultyClicked().toString())
                    Log.d("Advanced: ", qr.isAdvancedDifficultyClicked().toString())
                    Log.d("theme: ", qr.getTheme().toString())
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
                    qr.getTheme() === MainActivity.ThemeType.ADVANCED
                    //themeColor === qr.getTheme()
                    //logcat check
                    Log.d("Beginner: ", qr.isBeginnerDifficultyClicked().toString())
                    Log.d("Intermediate: ", qr.isIntermediateDifficultyClicked().toString())
                    Log.d("Advanced: ", qr.isAdvancedDifficultyClicked().toString())
                    Log.d("theme: ", qr.getTheme().toString())
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