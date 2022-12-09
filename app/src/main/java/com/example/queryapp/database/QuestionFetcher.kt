package com.example.queryapp.database

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream
import java.io.OutputStream


interface IQuestionFetcher {
    suspend fun fetchQuestion(): List<Question>
    suspend fun fetchQues(url: String): Bitmap?
    suspend fun fetchAndSave(url: String, file: OutputStream)
    suspend fun fetchBytes(url: String):InputStream?
}

class QuestionFetcher(ctx: Context) : IQuestionFetcher {
    private val contxt = ctx
    private val questions: MutableList<Question> = mutableListOf<Question>()
    private val URL = "https://my-json-server.typicode.com/JRichbow0/JSON/Questions"//"https://my-json-server.typicode.com/alexTU1/repo/Questions"
    private val client = OkHttpClient.Builder()
        .cache(
            Cache(
                File(ctx.cacheDir, "http_cache"),
                10 * 1024L * 1024L
            ))
        .build()

//    fun getQuestions(): List<Question?> {
//        val queue = Volley.newRequestQueue(contxt)
//        val qArray: Array<Question?> = arrayOfNulls(30)
//        val request = StringRequest(
//            com.android.volley.Request.Method.GET,
//            URL,
//            Response.Listener { response ->
//                val questions = response.toString()
//                var questionsArray = JSONArray(questions)
//                for(i in 0..questionsArray.length()-1) {
//                    var questionObject = questionsArray.getJSONObject(i)
//                    val q = Question(
//                        questionObject.getInt("ID"),
//                        questionObject.getString("Question"),
//                        questionObject.getString("Level")
//                    )
//                    qArray.set(i, q)
//                    Log.e("questionsArray", questionObject.toString())
//                }
//
//            },
//            Response.ErrorListener {
//
//            } )
//        queue.add(request)
//        Log.e("questionsArray", qArray.toString())
//        return listOf()
//    }

    override suspend fun fetchQuestion(): List<Question> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url(URL)
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if(responseBody != null) {
                val jsonString = responseBody.string()
                //Log.d("jsonString", jsonString.toString())
                val gson = Gson()
                val questionArray = gson.fromJson(jsonString, Array<Question>::class.java)
                Log.e("error", questionArray.toList().toString())
                questionArray.toList()
            } else {
                listOf()
            }
        }
    }

    override suspend fun fetchQues(url: String): Bitmap?{
        val stream = fetchBytes(url)
        return BitmapFactory.decodeStream(stream)
    }


    override suspend fun fetchAndSave(url:String, file: OutputStream){
        val stream = fetchBytes(url)
        file.bufferedWriter()
        val bytes = stream?.readBytes()
        if(bytes != null){
            file.write(bytes)
        }
    }

    override suspend fun fetchBytes(url:String): InputStream?{
        return withContext(Dispatchers.IO){
            val request = Request.Builder()
                .get()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            response.body?.byteStream()
        }
    }
}



