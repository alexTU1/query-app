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

        var quizQuestions: MutableList<Question> = mutableListOf<Question>()

        var questionIndex: MutableState<Int> = mutableStateOf(0)

        var questionString: MutableState<String> = mutableStateOf("")
        var optA: MutableState<String> = mutableStateOf("")
        var optB: MutableState<String> = mutableStateOf("")
        var optC: MutableState<String> = mutableStateOf("")
        var optD: MutableState<String> = mutableStateOf("")
        var correctOpt: MutableState<String> = mutableStateOf("")
        var truthValA: MutableState<Boolean> = mutableStateOf(false)
        var truthValB: MutableState<Boolean> = mutableStateOf(false)
        var truthValC: MutableState<Boolean> = mutableStateOf(false)
        var truthValD: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var _repository: IQuizRepository
    private val questionFetcher = QuestionFetcher(getApplication())

        init {
            viewModelScope.launch {
                _repository = QuizDatabaseRepository(getApplication())
                try {
                    _questions.value = questionFetcher.fetchQuestion()
                    populateQuestions(_questions.value)
                    Log.e("questionFetcher: ", questions.toString())
                    questionString.value = getFirstQuestion(_questions.value).question
                    optA.value = getFirstQuestion(_questions.value).optA
                    optB.value = getFirstQuestion(_questions.value).optB
                    optC.value = getFirstQuestion(_questions.value).optC
                    optD.value = getFirstQuestion(_questions.value).optD
                    correctOpt.value = getFirstQuestion(_questions.value).correctOpt

                    //truthVal.value
                    Log.d("questions: ", questionString.value)
                    Log.d("AnswerA: ", optA.value)
                    Log.d("AnswerB: ", optB.value)
                    Log.d("AnswerC: ", optC.value)
                    Log.d("AnswerD: ", optD.value)
                    Log.d("CorrectAnswer: ", correctOpt.value)
                    //Log.d("truth value: ", truthVal.value.toString())
                    questions = _questions
                    _questions.value.map{ question -> question.ID + 1 }
                }catch (e: Exception){
                    Toast.makeText(app, e.message, Toast.LENGTH_SHORT).show()
                }
               _questions.value = _repository.getQuestions()
            }
            Log.e("question list: ", _questions.value.toString())
        }

    fun getFirstQuestion(questions: List<Question>): Question{
        questions.forEach{question ->
            return question
        }
        return Question(-1, "", "", "", "", "", "")
    }

    fun populateQuestions(questions: List<Question>) {
        questions.forEach{question ->
            quizQuestions.add(question)
        }
    }

    fun getQuestionString(questions: List<Question>): String{
        //Log.e("IndexedQuestion", questions[questionIndex].question)
        return questionString.value
    }

    private fun getQuestionAnswerOptA(questions: List<Question>): String{
        return optA.value
    }

    private fun getQuestionAnswerOptB(questions: List<Question>): String{
        return optB.value
    }

    private fun getQuestionAnswerOptC(questions: List<Question>): String{
        return optC.value
    }

    private fun getQuestionAnswerOptD(questions: List<Question>): String{
        return optD.value
    }

    private fun getCorrectAnswerOpt(questions: List<Question>): String{
        return correctOpt.value
    }

    fun getTrueFalseValueA(): Boolean{
        truthValA.value = correctOpt.value == optA.value
        return truthValA.value
    }

    fun getTrueFalseValueB(): Boolean{
        truthValB.value = correctOpt.value == optB.value
        return truthValB.value
    }

    fun getTrueFalseValueC(): Boolean{
        truthValC.value = correctOpt.value == optC.value
        return truthValC.value
    }

    fun getTrueFalseValueD(): Boolean{
        truthValD.value = correctOpt.value == optD.value
        return truthValD.value
    }

    fun nextQuestion() {
        truthValA.value = false
        truthValB.value = false
        truthValC.value = false
        truthValD.value = false
        questionIndex.value == questionIndex.value++
        questionString.value = quizQuestions[questionIndex.value].question
        optA.value = quizQuestions[questionIndex.value].optA
        optB.value = quizQuestions[questionIndex.value].optB
        optC.value = quizQuestions[questionIndex.value].optC
        optD.value = quizQuestions[questionIndex.value].optD
        correctOpt.value = quizQuestions[questionIndex.value].correctOpt
    }

    fun reset() {
        truthValA.value = false
        truthValB.value = false
        truthValC.value = false
        truthValD.value = false
        questionIndex.value = 0
        questionString.value = ""
        optA.value = ""
        optB.value = ""
        optC.value = ""
        optD.value = ""
        correctOpt.value = ""
    }

}
