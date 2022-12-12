package com.example.queryapp

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.queryapp.impl.QuizRepository
import com.example.queryapp.navigation.ScreenHolder
import com.example.queryapp.pages.SubjectSelection.Subject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectSelection(navController: NavController?, qr: QuizRepository){
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
        SubjectSelectionPageView(navController = navController, qr)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectSelectionPageView(navController: NavController?, qr: QuizRepository){
    qr.reset()
    qr.resetNumCorrect()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectionRow(
            subjects = listOf(
            Subject(name ="Java Basics" , screenHolder = ScreenHolder.Quiz.route),
            Subject(name ="Object Oriented Programming Basics" , screenHolder = ScreenHolder.Quiz.route),
            Subject(name ="Object Oriented Programming Pillars" , screenHolder = ScreenHolder.Quiz.route),
            Subject(name ="Object Oriented Design Principles" , screenHolder = ScreenHolder.Quiz.route)),
            navController = navController,
            qr = qr
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectRow(
    idx: Int,
    subject: Subject,
    navController: NavController?,
){
    Column(
        modifier = Modifier.padding(horizontal = 10.dp, 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 120.dp)
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable (
                        onClick = {
                            navController?.navigate(route = ScreenHolder.Quiz.route)
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
                        text = subject.name,
                        color = colorResource(R.color.white),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun SelectionRow(
    subjects: List<Subject>,
    navController: NavController?,
    qr: QuizRepository
){
LazyColumn{
    itemsIndexed(subjects){ i, subject ->
        SubjectRow( idx = i, subject = subject, navController = navController)
    }
}
}


@Preview(showBackground = true)
@Composable
fun SubjectSelectionPreview() {
//    SubjectSelection(navController = null)
}