package com.example.queryapp.database

import android.app.Application
import androidx.room.Room


class QuizDatabaseRepository(app: Application, Question: List<Question>) : IQuizRepository {

    private val db: QuestionDatabase

    init {
        db = Room.databaseBuilder(
            app,
            QuestionDatabase::class.java,
            "questions.db"
        ).build()

    }


    override suspend fun getQuestion(): List<Question> {
        return db.quizDao().getQuestions()
    }
}