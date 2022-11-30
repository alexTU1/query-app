package com.example.queryapp


import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.queryapp.impl.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection


@Composable
fun Quiz(navController: NavController?, qr: QuizRepository) {

//    //val qr: QuizRepository = viewModel()
//    //val apiResponse = URL("https://my-json-server.typicode.com/alexTU1/repo/questions")
//    val wantedURL = "https://my-json-server.typicode.com/alexTU1/repo/questions"
//                //Adding variables to get information from the JSON objects
//                val jsonObject = JSONTokener(wantedURL).nextValue() as JSONObject
//                val jsonArray = jsonObject.getJSONArray("Questions")
//                for (i in 0 until jsonArray.length()) {
//                    //ID
//                    val questID = jsonArray.getJSONObject(i).getString("ID")
//                    Log.d("ID: ", questID)
//                    //Level
//                    val level = jsonArray.getJSONObject(i).getString("Level")
//                    Log.d("Level: ", level)
//                    //Question
//                    val question = jsonArray.getJSONObject(i).getString("Question")
//                    Log.d("Question: ", question)
//                }
//
//    inline fun <reified T> Context.getObjectFromJson(jsonFileName: String): T {
//        val myJson =LocalJSONParser.inputStreamToString(this.assets.open(jsonFileName))
//        return Gson().fromJson(myJson, T::class.java
//    }




    Column(
        modifier = Modifier
            .background(colorResource(R.color.medium_purple))
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .background(colorResource(R.color.light_purple))
        ){
            Text(
                text = stringResource(R.string.quiz_subject),
                color = colorResource(R.color.dark_purple),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15F)
                    .padding(top = 20.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(colorResource(R.color.medium_purple))
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
            Text(
                text = stringResource(R.string.sample_question),
                color = colorResource(R.color.white),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )
            AnswerOption(qr, navController,"A", stringResource(R.string.correct_answer), true)
            AnswerOption(qr, navController,"B", stringResource(R.string.incorrect_answer), false)
            AnswerOption(qr, navController,"C", stringResource(R.string.incorrect_answer), false)
            AnswerOption(qr, navController,"D", stringResource(R.string.incorrect_answer), false)
        }
    }
}


@Composable
fun AnswerOption(qr: QuizRepository, navController: NavController?, letter: String, optionString: String, isCorrect: Boolean){
    Button(
        onClick = {
            if(qr.getQuestionNum() < 10) {
                if(isCorrect){
                    qr.addPoint()
                }
                qr.nextQuestion()
            }
            else {
                navController?.navigate(route = ScreenHolder.QuizEnd.route)
                qr.reset()
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.99F)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.light_purple))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.white))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = letter,
                    fontSize = 18.sp,
                    color = colorResource(R.color.black)
                )
            }
            Text(
                text =  stringResource(R.string.incorrect_answer),
                fontSize = 15.sp,
                color = colorResource(R.color.dark_purple),
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun QuizPreview(){
    Quiz(navController = null, qr = viewModel())
}