package com.example.queryapp.database

abstract class AnswerMemoryRepository ( private var _answers: List<Answer> ) : IQuizRepository
{
    init {}

    override suspend fun getAnswers(): List<Answer> {
        return _answers
    }

}