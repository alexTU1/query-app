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
    val ID: UUID = UUID.randomUUID(),

    @ColumnInfo
    @SerializedName("Question")
    val question: String,

    @ColumnInfo
    @SerializedName("Level")
    val level: String,

    @ColumnInfo
    @SerializedName("Opt1")
    val optA: String,

    @ColumnInfo
    @SerializedName("Opt2")
    val optB: String,

    @ColumnInfo
    @SerializedName("Opt3")
    val optC: String,

    @ColumnInfo
    @SerializedName("Opt4")
    val optD: String,

    @ColumnInfo
    @SerializedName("Answer")
    val correctOpt: String
)


