package com.example.queryapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.database.QuestionListViewModel
import com.example.queryapp.impl.Question
import com.example.queryapp.impl.QuizRepository
import com.example.queryapp.submitContent.ConfirmBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Quiz(navController: NavController?, qr: QuizRepository) {
    val mBSState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val rCRS = rememberCoroutineScope()
    val isSelected = rememberSaveable{ mutableStateOf(true) }
    val ques: QuestionListViewModel = viewModel()

    ModalBottomSheetLayout(
        sheetContent = { MBSubmitButton(navController, isSelected, qr, rCRS, mBSState) },
        sheetBackgroundColor = Color.Transparent,
        sheetElevation = 10.dp,
        sheetState = mBSState,
        modifier = Modifier.fillMaxSize()
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.quiz_subject),
                            color = MaterialTheme.colors.primary,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold)

                    },
                    backgroundColor = MaterialTheme.colors.secondary,
                    modifier = Modifier.height(80.dp)
                )
            }
        ) {
            QuizPageView(navController = navController, qr = qr, rCRS, mBSState, ques)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuizPageView(
    navController: NavController?,
    qr: QuizRepository,
    rCRS: CoroutineScope,
    mBSState: ModalBottomSheetState,
    ques: QuestionListViewModel
)
{
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.primaryVariant)
        ){
            Row{
                Text(
                    text = "Question: ${qr.getQuestionNum()}/10",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .padding(25.dp)
                )
                LinearProgressIndicator(
                    progress = qr.getProgress(),
                    color=colorResource(R.color.white),
                    modifier = Modifier
                        .fillMaxWidth(0.99F)
                        .padding(25.dp)
                        .size(25.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            //val quesString = ques.getQuestionString(ques.questions.value)
            //Log.d("ques response", quesString)
            Text(
                text = (ques.questionString.value),
                color = colorResource(R.color.white),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )
            AnswerOption(qr, navController, rCRS, mBSState,"A", ques.optA.value, ques.getTrueFalseValueA())
            AnswerOption(qr, navController, rCRS, mBSState,"B", ques.optB.value, ques.getTrueFalseValueB())
            AnswerOption(qr, navController, rCRS, mBSState,"C", ques.optC.value, ques.getTrueFalseValueC())
            AnswerOption(qr, navController, rCRS, mBSState,"D", ques.optD.value, ques.getTrueFalseValueD())
        }
    }
}

@Composable
fun displayQuestions(ques: QuestionListViewModel): String {
    return ques.getQuestionString(ques.questions.value)
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnswerOption(qr: QuizRepository, navController: NavController?, coroutine: CoroutineScope, bottomSheetState: ModalBottomSheetState, letter: String, optionString: String, isCorrect: Boolean){
    Button(
        onClick = {
            qr.selectionMade(letter)
            if(qr.getQuestionNum() < 10) {
                qr.setFinalAnswer(isCorrect)
                coroutine.launch {
                    bottomSheetState.show()
                }
            }
            else {
                if (qr.getQuestionNum() == 10) {
                    if (isCorrect) {
                        qr.addPoint()
                    }
                    qr.setFinalAnswer(isCorrect)
                    coroutine.launch {
                        bottomSheetState.show()
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.99F)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor =
        if(bottomSheetState.isVisible && letter == qr.currentSelection())
            colorResource(R.color.white)
        else
            colorResource(R.color.light_purple))
    )
    {

        if(qr.getSubmitSelection() && qr.getQuestionNum() == 10){
            ConfirmBox(title = "Confirm", text = "Are you sure you want to submit?", navController = navController, qr, coroutine, bottomSheetState)
        }
        Row(
            horizontalArrangement = Arrangement.aligned(Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ){
            val backgroundColor =
                if(bottomSheetState.isVisible && letter == qr.currentSelection())
                    colorResource(R.color.light_purple)
                else if(qr.getShowAnswersValue()){
                    if(isCorrect)
                       colorResource(R.color.correct_answer)
                    else
                        colorResource(R.color.incorrect_answer)
                }
                else
                    colorResource(R.color.white)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(backgroundColor)
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter,
                        fontSize = 18.sp,
                        color =
                        if (bottomSheetState.isVisible && letter == qr.currentSelection())
                            colorResource(R.color.white)
                        else
                            colorResource(R.color.black)
                    )
                }
                Text(
                    text =  optionString,
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(15.dp)
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizPreview(){
    val qr: QuizRepository = viewModel()
    Quiz(navController = null, qr = qr)
}