package com.example.queryapp.impl

interface IQuizRepository {
    fun getQuestionNum(): Int
    fun nextQuestion(): Unit
}