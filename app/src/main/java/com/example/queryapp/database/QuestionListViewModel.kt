package com.example.queryapp.database

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class QuestionListViewModel(app: Application) : AndroidViewModel(app) {

        private val _questions: MutableState<List<Question>> = mutableStateOf(listOf())
        var questions: State<List<Question>> = _questions

        var questionString: MutableState<String> = mutableStateOf("")
        var optA: MutableState<String> = mutableStateOf("")
        var optB: MutableState<String> = mutableStateOf("")
        var optC: MutableState<String> = mutableStateOf("")
        var optD: MutableState<String> = mutableStateOf("")
        var correctOpt: MutableState<String> = mutableStateOf("")
        var truthVal: MutableState<Boolean> = mutableStateOf(false)

        private lateinit var _repository: IQuizRepository
        private val questionFetcher = QuestionFetcher(getApplication())

        init {
            viewModelScope.launch {
                _repository = QuizDatabaseRepository(getApplication())
                try {
                    _questions.value = questionFetcher.fetchQuestion()
                    Log.e("questionFetcher: ", questions.toString())
                    questionString.value = getQuestionString(_questions.value)
                    optA.value = getQuestionAnswerOptA(_questions.value)
                    optB.value = getQuestionAnswerOptB(_questions.value)
                    optC.value = getQuestionAnswerOptC(_questions.value)
                    optD.value = getQuestionAnswerOptD(_questions.value)
                    correctOpt.value = getCorrectAnswerOpt(_questions.value)

                    //truthVal.value
                    Log.d("questions: ", questionString.value)
                    Log.d("AnswerA: ", optA.value)
                    Log.d("AnswerB: ", optB.value)
                    Log.d("AnswerC: ", optC.value)
                    Log.d("AnswerD: ", optD.value)
                    Log.d("CorrectAnswer: ", correctOpt.value)
                    Log.d("truth value: ", truthVal.value.toString())
                    questions = _questions
                    _questions.value.map{ question -> question.ID + 1 }
                }catch (e: Exception){
                    Toast.makeText(app, e.message, Toast.LENGTH_SHORT).show()
                }
               _questions.value = _repository.getQuestions()
            }
            Log.e("question list: ", _questions.value.toString())
        }

    fun getQuestionString(questions: List<Question>): String{
       return questions.forEach{ question -> return question.question }.toString()
    }

    private fun getQuestionAnswerOptA(questions: List<Question>): String{
        return questions.forEach{ question -> return question.optA }.toString()
    }

    private fun getQuestionAnswerOptB(questions: List<Question>): String{
        return questions.forEach{ question -> return question.optB }.toString()
    }

    private fun getQuestionAnswerOptC(questions: List<Question>): String{
        return questions.forEach{ question -> return question.optC }.toString()
    }

    private fun getQuestionAnswerOptD(questions: List<Question>): String{
        return questions.forEach{ question -> return question.optD }.toString()
    }

    private fun getCorrectAnswerOpt(questions: List<Question>): String{
        return questions.forEach{ question -> return question.correctOpt }.toString()
    }

    fun getTrueFalseValueA(): Boolean{
        truthVal.value = correctOpt.value == optA.value
        return truthVal.value
    }

    fun getTrueFalseValueB(): Boolean{
        truthVal.value = correctOpt.value == optB.value
        return truthVal.value
    }

    fun getTrueFalseValueC(): Boolean{
        truthVal.value = correctOpt.value == optC.value
        return truthVal.value
    }

    fun getTrueFalseValueD(): Boolean{
        truthVal.value = correctOpt.value == optD.value
        return truthVal.value
    }

    fun increaseQuestionId(questions: List<Question>){
        questions
    }
}