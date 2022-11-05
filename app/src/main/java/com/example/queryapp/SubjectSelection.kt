package com.example.queryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectSelection(navController: NavController?){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.dark_purple)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .background(colorResource(R.color.light_purple))
        ){
            Text(
                text = stringResource(R.string.subject_selection),
                color = colorResource(R.color.dark_purple),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15F)
                    .padding(top = 20.dp)
            )
        }
        SelectionRow(subject1 = "Sample Subject", subject2 = "Sample Subject", navController, ScreenHolder.Quiz.route)
        SelectionRow(subject1 = "Sample Subject", subject2 = "Sample Subject", navController, ScreenHolder.Quiz.route)
        SelectionRow(subject1 = "Sample Subject", subject2 = "Sample Subject", navController, ScreenHolder.Quiz.route)
        SelectionRow(subject1 = "Sample Subject", subject2 = "Sample Subject", navController, ScreenHolder.Quiz.route)
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectionRow(
    subject1: String, //text of left card
    subject2: String, //text of right card
    navController: NavController?,
    screenHolder: String //route to quiz
){
    Column(
        modifier = Modifier.padding(horizontal = 20.dp, 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .size(width = 180.dp, height = 120.dp)
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable (
                        onClick = {
                            navController?.navigate(route = screenHolder) {
                                popUpTo(ScreenHolder.Landing.route) {
                                    inclusive = true
                                }
                            }
                        }
                        ),
                backgroundColor = colorResource(R.color.medium_purple),
                elevation = 20.dp

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = subject1,
                        color = colorResource(R.color.white),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }
            Card(
                elevation = 20.dp,
                modifier = Modifier
                    .size(width = 180.dp, height = 120.dp)
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable(
                        onClick = {
                            navController?.navigate(route = screenHolder) {
                                popUpTo(ScreenHolder.Landing.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ),
                backgroundColor = colorResource(R.color.medium_purple)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = subject2,
                        color = colorResource(R.color.white),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SubjectSelectionPreview() {
    SubjectSelection(navController = null)
}