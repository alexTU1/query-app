package com.example.queryapp.database

import androidx.room.*
import com.example.queryapp.navigation.ScreenHolder
import java.util.*


@Dao
interface QuizDao{
    @Query("select * from questions")
    suspend fun getQuestions(): List<Question>
}

class UUIDConverter{
    @TypeConverter
    fun stringToUUID(uuid: String): UUID{
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun uuidToString(uuid: UUID): String {
        return uuid.toString()
    }
}




@Database(entities = [Question::class], version = 1, exportSchema = false)
        abstract class QuestionDatabase : RoomDatabase(){
            abstract fun quizDao(): QuizDao
        }