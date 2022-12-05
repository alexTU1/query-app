package com.example.queryapp.database

interface IQuizRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun getAnswers(): List<Answer>
}