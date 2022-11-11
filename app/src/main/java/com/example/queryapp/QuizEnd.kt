package com.example.queryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.impl.QuizRepository

@Composable
fun QuizEnd(navController: NavController?, qr: QuizRepository) {
    PageScreen(navController, qr)
}

@Composable
fun PageScreen(navController: NavController?, qr: QuizRepository) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.dark_purple)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(R.color.medium_purple)),
            contentAlignment =  Alignment.Center
        ) {
            ScoreContent(qr)
        }
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(colorResource(R.color.light_purple)),
            contentAlignment =  Alignment.Center
        ) {
            NextSelectionContent(navController, qr)
        }

    }
}

@Composable
fun ScoreContent(qr: QuizRepository) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.final_score),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_purple)
        )
        Text(
            modifier = Modifier.padding(0.dp),
            text = "${qr.getQuizResult()}/10",
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white)
        )
        Text(
            text = stringResource(R.string.score_descript_1),
            fontSize = 20.sp,
            color = colorResource(R.color.white)
        )
    }

}

@Composable
fun NextSelectionContent(navController: NavController?, qr: QuizRepository) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(300.dp)
    ) {
        Text(
            text = stringResource(R.string.congrats),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_purple)
        )
        Text(
            text = stringResource(R.string.try_or_move_on),
            fontSize = 20.sp,
            color = colorResource(R.color.white)
        )
        Button(
            onClick = {
                navController?.navigate(route = ScreenHolder.Quiz.route){
                    popUpTo(ScreenHolder.Quiz.route){
                        inclusive = true
                    }
                }
                qr.resetNumCorrect()
            },
            modifier = Modifier
                .width(300.dp)
                .padding(vertical = 15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.medium_purple),
            )
        ) {
            Text(
                text = stringResource(R.string.start_over),
                color = colorResource(R.color.white)
            )
        }
        Button(
            onClick = {
                navController?.navigate(route = ScreenHolder.Landing.route) {
                    popUpTo(ScreenHolder.Landing.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.medium_purple),
            )
        ) {
            Text(
                text = stringResource(R.string.next_set),
                color = colorResource(R.color.white)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun QuizEndPreview() {
    QuizEnd(navController = null, qr = viewModel())
}