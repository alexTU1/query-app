package com.example.queryapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Answers")
    data class Answer(

    @PrimaryKey
    val id: UUID,

    @ColumnInfo
    val optA: String,

    @ColumnInfo
    val optB: String,

    @ColumnInfo
    val optC: String,

    @ColumnInfo
    val optD: String,

    @ColumnInfo
    val correctOpt: String
)
