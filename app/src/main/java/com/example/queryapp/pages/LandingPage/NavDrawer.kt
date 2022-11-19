package com.example.queryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.queryapp.pages.LandingPage.NavDrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@Composable
//fun NavDrawer(){
//    NavDrawerHeader()
//    NavDrawerContent(drawerItems = , whenClicked = )
//}


@Composable
fun NavDrawerHeader(
    state: ScaffoldState,
    scope: CoroutineScope
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 30.dp)
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.CenterStart
    ) {
       Text(
           text = "Query",
           fontSize = 60.sp,
           fontWeight= FontWeight.Bold,
           color = MaterialTheme.colors.secondary,
           modifier = Modifier.clickable {
               scope.launch {
                   state.drawerState.close()
               }
           }
       )
    }
}

@Composable
fun NavDrawerContent(
    drawerItems: List<NavDrawerItem>,
    modifier: Modifier = Modifier,
    whenClicked: (NavDrawerItem) -> Unit
){
    LazyColumn(modifier){
        items(drawerItems){ drawerItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( horizontal = 30.dp, vertical = 15.dp)
                    .clickable { whenClicked(drawerItem) }
                    .height(20.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Icon(imageVector = drawerItem.icon, contentDescription = "", tint = MaterialTheme.colors.secondary)
                Text(text = drawerItem.name,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp))
            }
        }
    }

}