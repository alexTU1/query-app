package com.example.queryapp

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
import androidx.navigation.NavController
import com.example.queryapp.navigation.ScreenHolder

@Composable
fun MBSubmitButton(
    navController: NavController?,
    isSelected: MutableState<Boolean>
){
   Button(
       onClick = {
           isSelected.value == false
                 if(isSelected.value == true){
                   navController?.navigate(route = ScreenHolder.QuizEnd.route)
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