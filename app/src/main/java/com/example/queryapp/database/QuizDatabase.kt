package com.example.queryapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.queryapp.navigation.ScreenHolder


@Dao
interface QuizDao{
    @Query("select * from questions")
    suspend fun getQuestions(): List<Question>


}

@Database(entities = [Question::class], version = 1, exportSchema = false)
        abstract class QuestionDatabase : RoomDatabase(){
            abstract fun quizDao(): QuizDao
        }