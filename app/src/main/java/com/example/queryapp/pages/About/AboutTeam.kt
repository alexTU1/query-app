package com.example.queryapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AboutTeam(navController: NavController?){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "<About Us/>",color = MaterialTheme.colors.primary,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)},
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.height(80.dp),
                elevation = 0.dp
            )
        }
    ) {
        AboutTeamContent()
    }

}

@Composable
fun AboutTeamContent(){
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center ,
            modifier = Modifier.padding(vertical = 60.dp)
        ) {
            CardContent(name = "Jeniah \nRichbow", major = "major: Computer Science", image = Icons.Default.Person )
            Spacer(modifier = Modifier.height(25.dp))
            CardContentReversed(name = "Deamri \nSimms", major = "major: Computer Science", image = Icons.Default.Person )
            Spacer(modifier = Modifier.height(25.dp))
            CardContent(name = "Alexander \nCommodore", major = "major: Computer Science", image = Icons.Default.Person )
        }
    }
}

@Composable
fun CardContent(
    name: String,
    major: String,
    image: ImageVector
){
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp, 100.dp, 100.dp, 20.dp))
            .height(160.dp)
            .width(320.dp)
            .padding(vertical = 0.dp),
        backgroundColor = MaterialTheme.colors.secondary
    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.width(140.dp)
            ) {
                Text(name, textAlign = TextAlign.Start, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(major, color = Color.White, fontSize = 12.sp)
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primaryVariant)
                    .size(140.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = image, contentDescription = "", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(120.dp))
            }

        }
    }
}

@Composable
fun CardContentReversed(
    name: String,
    major: String,
    image: ImageVector
){
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp, 20.dp, 20.dp, 100.dp))
            .height(160.dp)
            .width(320.dp)
            .padding(vertical = 0.dp),
        backgroundColor = MaterialTheme.colors.secondary
    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primaryVariant)
                    .size(140.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(imageVector = image, contentDescription = "", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(120.dp))
            }
            Column(
                modifier = Modifier.width(140.dp)
            ) {
                Text(name, textAlign = TextAlign.Start, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(major, color = Color.White, fontSize = 12.sp)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutTeamPreview() {
    AboutTeam(navController = null)
}