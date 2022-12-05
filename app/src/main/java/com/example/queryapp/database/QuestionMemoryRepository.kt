package com.example.queryapp.database

abstract class QuestionMemoryRepository (private var _questions: List<Question>) : IQuizRepository{
    init {
    }
     suspend fun getQuestions(): List<Question>{
        return _questions
    }
}