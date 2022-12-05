package com.example.queryapp.worker


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.queryapp.ScreenHolder


@Composable
fun NotificationMessage(navController: NavController) {
    val context = LocalContext.current
    Column (
        modifier = Modifier
        .fillMaxWidth()
    ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
            ){
        OutlinedButton(onClick = {
            val noti = Notify(context,"?ueryApp","This is a quiz on OOP." )
            noti.buildingNotification()
            navController?.navigate(route = ScreenHolder.Landing.route)
        })
        {
            Text(text = "Before you start...", fontSize = 16.sp
            )
        }
    }
}

