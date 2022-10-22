package com.example.queryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queryapp.ui.theme.QueryappTheme

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
                    QuizScreen()
                }
            }
        }
    }
}


@Composable
fun QuizScreen() {
    val correctAnswerString = "The Correct Answer to the hypothetical question"
    val incorrectAnswerString = "The Incorrect Answer to the hypothetical question"
    Column(
        modifier = Modifier
            .background(colorResource(R.color.quiz_background))
    ){
        Box(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .background(colorResource(R.color.light_purple))
        ){
            Text(
                text = " Quiz Subject",
                color = colorResource(R.color.title_color),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15F)
                    .padding(top = 20.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.quiz_background))
        ){
            Text(
                text = "Query: 1/10",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white),
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(
                text = "This is some random sample question text. I couldn't think of a question so I am writing this blob that means absolutely nothing.",
                color = colorResource(R.color.white),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .padding(20.dp)
            )
            answerOption("A", correctAnswerString)
            answerOption("B", incorrectAnswerString)
            answerOption("C", incorrectAnswerString)
            answerOption("D", incorrectAnswerString)
        }
    }
}

@Composable
fun answerOption(letter: String, optionString: String){
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(0.99F)
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.light_purple))
    ){
        Row(){
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.white))
                    .padding(20.dp)
            ){
                Text(
                    text= letter,
                    fontSize = 18.sp,
                    color = colorResource(R.color.black),


                )
            }
            Text(
                text = optionString,
                fontSize = 15.sp,
                color = colorResource(R.color.title_color),
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QueryappTheme {
        QuizScreen()
    }
}