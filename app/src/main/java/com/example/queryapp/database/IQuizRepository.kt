package com.example.queryapp.database

interface IQuizRepository {
    suspend fun getQuestion(): List<Question>
}