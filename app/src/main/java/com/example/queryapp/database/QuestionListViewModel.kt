package com.example.queryapp.database

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.queryapp.impl.QuizRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class QuestionListViewModel(app: Application) : AndroidViewModel(app) {

    private val _questions: MutableState<List<Question>> = mutableStateOf(listOf())
    var questions: State<List<Question>> = _questions

    private var quizQuestions: MutableList<Question> = mutableListOf()

    private var beginnerQuestions: MutableList<Question> = mutableListOf()
    private var intermediateQuestions: MutableList<Question> = mutableListOf()
    private var advancedQuestions: MutableList<Question> = mutableListOf()

    private lateinit var beginnerFirstQuestion: Question
    private lateinit var intermediateFirstQuestion: Question
    private lateinit var advancedFirstQuestion: Question


    private var questionIndex: MutableState<Int> = mutableStateOf(0)

    var questionString: MutableState<String> = mutableStateOf("")
    var optA: MutableState<String> = mutableStateOf("")
    var optB: MutableState<String> = mutableStateOf("")
    var optC: MutableState<String> = mutableStateOf("")
    var optD: MutableState<String> = mutableStateOf("")
    private var correctOpt: MutableState<String> = mutableStateOf("")
    private var truthValA: MutableState<Boolean> = mutableStateOf(false)
    private var truthValB: MutableState<Boolean> = mutableStateOf(false)
    private var truthValC: MutableState<Boolean> = mutableStateOf(false)
    private var truthValD: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var _repository: IQuizRepository
    private val questionFetcher = QuestionFetcher(getApplication())

    private var difficultyLevel: DifficultyLevel = DifficultyLevel.BEGINNER

    enum class DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    private var qr: QuizRepository? = null

    init {
        viewModelScope.launch {
            _repository = QuizDatabaseRepository(getApplication())
            try {
                questionFetcher.fetchQuestion("https://my-json-server.typicode.com/JRichbow0/JSON/Beginner").forEach{question ->
                    if(question.ID == 0){
                        beginnerFirstQuestion = question
                    }
                    beginnerQuestions.add(question)
                }
                questionFetcher.fetchQuestion("https://my-json-server.typicode.com/dsimms360/querydb/Intermediate").forEach{question ->
                    if(question.ID == 11){
                        intermediateFirstQuestion = question
                    }
                    intermediateQuestions.add(question)
                }
                questionFetcher.fetchQuestion("https://my-json-server.typicode.com/alexTU1/repo/Advanced").forEach{question ->
                    if(question.ID == 21){
                        advancedFirstQuestion = question
                    }
                    advancedQuestions.add(question)
                }
                questions = _questions
                _questions.value.map{ question -> question.ID + 1 }
            }catch (e: Exception){
                Toast.makeText(app, e.message, Toast.LENGTH_SHORT).show()
            }
            _questions.value = _repository.getQuestions()
        }
    }

    fun getFirstQuestion(difficulty: Int) {
        viewModelScope.launch {
            delay(1000)
            when (difficulty) {
                1 -> {
                    quizQuestions = beginnerQuestions
                    difficultyLevel = DifficultyLevel.BEGINNER
                }
                2 -> {
                    quizQuestions = intermediateQuestions
                    difficultyLevel = DifficultyLevel.INTERMEDIATE
                }
                3 -> {
                    quizQuestions = advancedQuestions
                    difficultyLevel = DifficultyLevel.ADVANCED
                }
            }
            when (difficultyLevel) {
                DifficultyLevel.BEGINNER -> {
                    questionString.value = beginnerFirstQuestion.question
                    optA.value = beginnerFirstQuestion.optA
                    optB.value = beginnerFirstQuestion.optB
                    optC.value = beginnerFirstQuestion.optC
                    optD.value = beginnerFirstQuestion.optD
                    correctOpt.value = beginnerFirstQuestion.correctOpt
                }
                DifficultyLevel.INTERMEDIATE -> {
                    questionString.value = intermediateFirstQuestion.question
                    optA.value = intermediateFirstQuestion.optA
                    optB.value = intermediateFirstQuestion.optB
                    optC.value = intermediateFirstQuestion.optC
                    optD.value = intermediateFirstQuestion.optD
                    correctOpt.value = intermediateFirstQuestion.correctOpt
                }
                DifficultyLevel.ADVANCED -> {
                    questionString.value = advancedFirstQuestion.question
                    optA.value = advancedFirstQuestion.optA
                    optB.value = advancedFirstQuestion.optB
                    optC.value = advancedFirstQuestion.optC
                    optD.value = advancedFirstQuestion.optD
                    correctOpt.value = advancedFirstQuestion.correctOpt
                }
            }
        }
    }

    fun setQuizRepository(QR: QuizRepository){
        qr = QR
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
        questionIndex.value = questionIndex.value++
        questionString.value = quizQuestions[qr?.getQuestionNum()!! - 1].question
        optA.value = quizQuestions[qr?.getQuestionNum()!! - 1].optA
        optB.value = quizQuestions[qr?.getQuestionNum()!! - 1].optB
        optC.value = quizQuestions[qr?.getQuestionNum()!! - 1].optC
        optD.value = quizQuestions[qr?.getQuestionNum()!! - 1].optD
        correctOpt.value = quizQuestions[qr?.getQuestionNum()!! - 1].correctOpt
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
