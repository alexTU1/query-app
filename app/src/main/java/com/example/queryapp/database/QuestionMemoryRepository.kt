package com.example.queryapp.database

abstract class QuestionMemoryRepository (
    private var _questions: List<Question>,
    private var _answers: List<Answer>) : IQuizRepository
{
    init {}

     override suspend fun getQuestions(): List<Question>{
        return _questions
    }

    override suspend fun getAnswers(): List<Answer> {
        return _answers
    }

}