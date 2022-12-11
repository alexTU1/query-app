package com.example.queryapp.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "questions")
data class Question(
    @PrimaryKey
    @SerializedName("ID")
    val ID: Int,

    @ColumnInfo
    @SerializedName("Question")
    val question: String,

    @ColumnInfo
    @SerializedName("Level")
    val level: String,

    @ColumnInfo
    @SerializedName("OptA")
    val optA: String,

    @ColumnInfo
    @SerializedName("OptB")
    val optB: String,

    @ColumnInfo
    @SerializedName("OptC")
    val optC: String,

    @ColumnInfo
    @SerializedName("OptD")
    val optD: String,

    @ColumnInfo
    @SerializedName("correctOpt")
    val correctOpt: String
)


