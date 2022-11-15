package com.example.queryapp.impl

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizRepository : ViewModel() {
    private val question_Num: MutableState<Int> = mutableStateOf(1)

    private val progress: MutableState<Float> = mutableStateOf(0.0F)

    private val numCorrect: MutableState<Int> = mutableStateOf(0)

    private val beginnerDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val intermediateDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val advancedDifficultyClick: MutableState<Boolean> = mutableStateOf(false)

    init{}

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

    fun resetNumCorrect(){
        numCorrect.value = 0
    }


    //for app theme changes
    fun isBeginnerDifficultyClicked(): Boolean {
        beginnerDifficultyClick.value = !beginnerDifficultyClick.value
        intermediateDifficultyClick.value
        advancedDifficultyClick.value
        return beginnerDifficultyClick.value
    }
//    fun getBClickVal():Boolean{
//        return beginnerDifficultyClick.value
//    }

    fun isIntermediateDifficultyClicked(): Boolean {
        intermediateDifficultyClick.value = !intermediateDifficultyClick.value
        beginnerDifficultyClick.value
        advancedDifficultyClick.value
        return intermediateDifficultyClick.value
    }
//    fun getIClickVal():Boolean{
//        return intermediateDifficultyClick.value
//    }

    fun isAdvancedDifficultyClicked(): Boolean{
        advancedDifficultyClick.value = !advancedDifficultyClick.value
        beginnerDifficultyClick.value
        intermediateDifficultyClick.value
        return advancedDifficultyClick.value
    }
//    fun getAClickVal():Boolean{
//        return advancedDifficultyClick.value
//    }

}