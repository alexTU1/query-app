package com.example.queryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queryapp.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QueryappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LandingPage()
                }
            }
        }
    }
}


@Composable
fun LandingPage() {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.medium_purple))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Welcome",
            fontSize = 42.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 80.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetStarted()
    }
}

@Composable
fun GetStarted(){
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
                text = "Get Started",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Gray
            )
            Spacer(modifier =  Modifier.height(10.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                lightPurple,
                                darkPurple
                            )
                        )
                    )
            )
            {
                Text(
                    text = "Beginner",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                lightBlue,
                                darkCyan
                            )
                        )
                    )
            ) {
                Text(text = "Intermediate",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.Serif,
                    color = Color.White)
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .height(130.dp)
                    .width(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                lightPeach,
                                mediumPeach
                            )
                        )
                    )
            ) {
                Text(text = "Advanced",fontSize = 28.sp,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.Serif,
                    color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QueryappTheme {
        LandingPage()
        GetStarted()
    }
}