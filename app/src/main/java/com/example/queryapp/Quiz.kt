package com.example.queryapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.queryapp.impl.QuizRepository

@Composable
fun Quiz(navController: NavController?) {

    val qr = QuizRepository()

    val questionNum = remember{mutableStateOf(qr.getQuestionNum().toString())}
    Log.d(questionNum.toString(), " ")

    val progress: MutableState<Float> = remember{mutableStateOf(0.1F/qr.getQuestionNum())}
    Log.d(progress.toString(), " ")

    Column(
        modifier = Modifier
            .background(colorResource(R.color.medium_purple))
    ){
        Box(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .background(colorResource(R.color.light_purple))
        ){
            Text(
                text = stringResource(R.string.quiz_subject),
                color = colorResource(R.color.dark_purple),
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
                .fillMaxHeight()
                .background(colorResource(R.color.medium_purple))
        ){
            Row{
                Text(
                    text = "Question: ${questionNum.value}/10",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .padding(25.dp)
                )
                LinearProgressIndicator(
                    progress = progress.value,
                    color=colorResource(R.color.white),
                    modifier = Modifier
                        .fillMaxWidth(0.99F)
                        .padding(25.dp)
                        .size(25.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Text(
                text = stringResource(R.string.sample_question),
                color = colorResource(R.color.white),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )
            AnswerOption(qr, navController,"A", stringResource(R.string.correct_answer))
            AnswerOption(qr, navController,"B", stringResource(R.string.incorrect_answer))
            AnswerOption(qr, navController,"C", stringResource(R.string.incorrect_answer))
            AnswerOption(qr, navController,"D", stringResource(R.string.incorrect_answer))
        }
    }
}

@Composable
fun AnswerOption(qr: QuizRepository, navController: NavController?, letter: String, optionString: String){
    Button(
        onClick = {
            if(qr.getQuestionNum() == 10) {
                qr.reset()
                navController?.navigate(route = ScreenHolder.QuizEnd.route)
            }
            else {
                qr.nextQuestion()
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.99F)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.light_purple))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.white))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text= letter,
                    fontSize = 18.sp,
                    color = colorResource(R.color.black)
                    )
            }
            Text(
                text = optionString,
                fontSize = 15.sp,
                color = colorResource(R.color.dark_purple),
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizPreview(){
    Quiz(navController = null)
}