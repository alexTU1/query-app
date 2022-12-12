package com.example.queryapp.submitContent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queryapp.impl.QuizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MBSubmitButton(
    isSelected: MutableState<Boolean>,
    qr: QuizRepository,
    rCRS: CoroutineScope,
    mBSState: ModalBottomSheetState
){
   Button(
       onClick = {
           if(isSelected.value){
               rCRS.launch {
                   mBSState.hide()
                   if(qr.getQuestionNum() < 10) {
                       qr.displayAnswers()
                       delay(800)
                       qr.hideAnswers()
                       qr.nextQuestion()
                   }
               }
               qr.resetAnswerSelection()
               if(qr.getQuestionNum() < 10){
                   if(qr.getFinalAnswer()){
                       qr.addPoint()
                   }
               }
               else {
                   qr.setSubmitSelection()
               }
           }
       },
       modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp)
           .clip(RoundedCornerShape(40.dp))
           .height(60.dp),
       colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onBackground)
   ) {
       Text(text = "SUBMIT",
       fontSize = 20.sp,
       color = Color.White)
   }
}