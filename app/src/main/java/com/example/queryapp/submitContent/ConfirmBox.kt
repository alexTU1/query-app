package com.example.queryapp.submitContent

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.QuizEnd
import com.example.queryapp.navigation.ScreenHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun ConfirmBox(
    title: String,
    text: String,
    navController: NavController?,
    //cvm: ConfirmBoxViewModel
){
    val showConfirmBoxs = MutableStateFlow(false)
    val showConfirmBox: StateFlow<Boolean> = showConfirmBoxs.asStateFlow()

    fun onDismiss(){
        showConfirmBoxs.value = false

    }
    AlertDialog(

        onDismissRequest = {

                 onDismiss()
        },
        title = {Text(title)},
        text = {Text(text)},
        confirmButton = {
            Button(
                onClick = {
                    navController?.navigate(route = ScreenHolder.QuizEnd.route)
                }
            ){
               Text("SUBMIT")
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)

    )



}

//@Preview(showBackground = true)
//@Composable
//fun ConfirmBoxPreview() {
//    ConfirmBox()
//}