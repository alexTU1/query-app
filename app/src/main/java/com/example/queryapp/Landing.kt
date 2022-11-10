package com.example.queryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Landing(navController: NavController?) {
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
        GetStarted(navController)
    }
}

@Composable
fun GetStarted(navController: NavController?){
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
                    navController?.navigate(route = ScreenHolder.Quiz.route)
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
                    navController?.navigate(route = ScreenHolder.Quiz.route)
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
    Landing(navController = null)
}