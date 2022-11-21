package com.example.queryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.queryapp.navigation.ScreenHolder

@Composable
fun AboutQuery(navController: NavController?){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "<Query/>",color = MaterialTheme.colors.primary,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)},
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.height(80.dp),
                elevation = 0.dp
            )
        }
    ) {
        AboutQueryContent(navController)
    }
}

@Composable
fun AboutQueryContent(navController: NavController?){
    Column(
        modifier = Modifier
        //.padding(horizontal = 30.dp, vertical = 20.dp)
        .background(MaterialTheme.colors.primary)
        .fillMaxWidth()
        .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContentTemp(title = "Origin", sampleText = "Lorem ipsum dolor sit amet consectetur. Nulla morbi at urna tempus tincidunt neque auctor diam ut. Quam nunc vulputate non fermentum vel morbi. Felis eu neque aliquam congue ullamcorper cursus et aliquam mattis. Pulvinar consequat enim turpis suscipit in.")
        ContentTemp(title = "Purpose", sampleText = "Lorem ipsum dolor sit amet consectetur. Nulla morbi at urna tempus tincidunt neque auctor diam ut. Quam nunc vulputate non fermentum vel morbi.")
        ContentTemp(title = "Tools", sampleText = "Lorem ipsum dolor sit amet consectetur. Viverra scelerisque vulputate pharetra rutrum massa.")
        MeetTheTeam(navController)
    }
}

@Composable
fun ContentTemp(
    title: String,
    sampleText: String
){
    Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 30.dp)) {
        Text(text = title, fontSize = 25.sp, color = MaterialTheme.colors.primaryVariant)
        Text(text = sampleText, fontSize = 15.sp, color = Color.White)
    }
}

@Composable
fun MeetTheTeam(navController: NavController?){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 40.dp)
    ) {
        Text(text = "Meet The Team", fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(80.dp)
                .width(290.dp)
                .background(Color.White)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
               OutlinedButton(
                   onClick = {
                       navController?.navigate(route = ScreenHolder.Landing.route){
                           popUpTo(ScreenHolder.Landing.route){
                               inclusive = true
                           }
                       }
                   },
                   modifier = Modifier.size(110.dp, 40.dp),
                   shape = RoundedCornerShape(20.dp),
                   colors = ButtonDefaults.buttonColors(Color.Transparent),
                   border = BorderStroke(1.dp, color = MaterialTheme.colors.primary )
               ) {
                    Text("HOME", color = MaterialTheme.colors.primary)
               }
               Button(
                   onClick = {
                       navController?.navigate(route = ScreenHolder.AboutTeam.route){
                           popUpTo(ScreenHolder.AboutQuery.route){
                               inclusive = true
                           }
                       }
                   },
                   modifier = Modifier.size(110.dp, 40.dp),
                   shape = RoundedCornerShape(20.dp)
               ) {
                    Text("TEAM")
               }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    AboutQuery(navController = null)
}