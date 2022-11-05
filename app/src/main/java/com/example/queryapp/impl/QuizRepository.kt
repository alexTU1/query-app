package com.example.queryapp.impl

class QuizRepository : IQuizRepository {
    private var questionNum = 1

    init{}

    override fun getQuestionNum(): Int {
        return questionNum
    }

    override fun nextQuestion() {
        questionNum++
    }

    fun reset() {
        questionNum = 1
    }
}