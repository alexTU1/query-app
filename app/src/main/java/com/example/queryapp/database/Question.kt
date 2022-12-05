package com.example.queryapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey
    val id: UUID,

    @ColumnInfo
    val question: String,

    @ColumnInfo
    val level: String

){}