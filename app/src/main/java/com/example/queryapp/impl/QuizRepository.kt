package com.example.queryapp.impl

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizRepository : ViewModel() {
    private val question_Num: MutableState<Int> = mutableStateOf(1)

    private val progress: MutableState<Float> = mutableStateOf(0.0F)

    private val numCorrect: MutableState<Int> = mutableStateOf(0)

    private val questions: List<Question?> = mutableListOf(null)

    init{

    }

    fun getQuestionNum(): Int {
        return question_Num.value
    }

    fun nextQuestion() {
        question_Num.value++
        progress.value = 0.1F*(question_Num.value - 1)
    }

    fun getProgress() : Float {
        return progress.value
    }

    fun addPoint() {
        numCorrect.value++
    }

    fun getQuizResult(): Int {
        return numCorrect.value
    }

    fun reset() {
        question_Num.value = 1
        progress.value = 0.0F
    }

    fun newQuiz() {

    }
}