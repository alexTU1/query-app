package com.example.queryapp.submitContent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.QuizEnd
import com.example.queryapp.database.QuestionListViewModel
import com.example.queryapp.impl.QuizRepository
import com.example.queryapp.navigation.ScreenHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmBox(
    title: String,
    text: String,
    navController: NavController?,
    qr: QuizRepository,
    ques: QuestionListViewModel,
    coroutine: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
    //cvm: ConfirmBoxViewModel
) {
    val showConfirmBoxs = MutableStateFlow(false)
    //val showConfirmBox: StateFlow<Boolean> = showConfirmBoxs.asStateFlow()

    fun onDismiss() {
        showConfirmBoxs.value = false
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(title, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Medium) },
        text = { Text(text, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Normal) },
        confirmButton = {
            Button(
                onClick = {
                    coroutine.launch{
                        qr.displayAnswers()
                        delay(800)
                        qr.hideAnswers()
                        navController?.navigate(route = ScreenHolder.QuizEnd.route)
                        qr.reset()
                        ques.reset()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            ) {
                Text("YES")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    qr.setSubmitSelection()
                    coroutine.launch {
                        bottomSheetState.hide()
                    }
                    if (qr.getFinalAnswer()) {
                        qr.subtractPoint()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, color = MaterialTheme.colors.primary )
            ) {
                Text("NO", color = MaterialTheme.colors.primary)
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.secondary
    )
}
