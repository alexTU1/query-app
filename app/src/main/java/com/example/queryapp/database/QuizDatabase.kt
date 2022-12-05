package com.example.queryapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.queryapp.ScreenHolder


@Dao
interface QuizDao{
     fun getQuestions(): List<Question>
     //@Query("select * from questions")

}

@Database(entities = [ScreenHolder.Quiz::class], version = 1, exportSchema = false)
        abstract class QuestionDatabase : RoomDatabase(){
            abstract fun quizDao(): QuizDao
        }