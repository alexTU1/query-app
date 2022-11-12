package com.example.queryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.subject_selection),
                        color = MaterialTheme.colors.primary,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold)

                },
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.height(80.dp)
            )
        }
    ) {
        SubjectSelectionPageView(navController = navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectSelectionPageView(navController: NavController?){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                backgroundColor = MaterialTheme.colors.primaryVariant,
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
                backgroundColor = MaterialTheme.colors.primaryVariant

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