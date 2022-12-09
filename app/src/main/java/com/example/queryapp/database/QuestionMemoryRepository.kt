package com.example.queryapp.database

abstract class QuestionMemoryRepository ( private var _questions: List<Question> ) : IQuizRepository
{
    init {}

     override suspend fun getQuestions(): List<Question>{
        return _questions
    }


}