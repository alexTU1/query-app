package com.example.queryapp.database

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request


interface IQuestionFetcher {
    suspend fun fetchQuestion(): List<Question>
}

class QuestionFetcher() : IQuestionFetcher {
    private val URL = "https://my-json-server.typicode.com/alexTU1/repo/db"
    private val client = OkHttpClient.Builder()
        .build()

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
                val gson = Gson()
                val songsArray = gson.fromJson(jsonString, Array<Question>::class.java)
                songsArray.toList()
            } else {
                listOf()
            }
        }
    }
}