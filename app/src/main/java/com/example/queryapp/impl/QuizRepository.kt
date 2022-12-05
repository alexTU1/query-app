package com.example.queryapp.impl

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.queryapp.MainActivity
import com.example.queryapp.database.IQuizRepository
import com.example.queryapp.database.QuestionFetcher
import com.example.queryapp.database.QuizDatabaseRepository
import com.example.queryapp.pages.SubjectSelection.Subject
import kotlinx.coroutines.launch


class QuizRepository(app: Application) : AndroidViewModel(app) {
    private val question_Num: MutableState<Int> = mutableStateOf(1)

    private val progress: MutableState<Float> = mutableStateOf(0.0F)

    private val numCorrect: MutableState<Int> = mutableStateOf(0)

    private val questions: List<Question?> = mutableListOf(null)


    private val optionSelected: MutableState<Boolean> = mutableStateOf(false)

    private val submitSelected: MutableState<Boolean> = mutableStateOf(false)

    private val finalAnswer: MutableState<Boolean> = mutableStateOf(false)

    private val beginnerDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val intermediateDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val advancedDifficultyClick: MutableState<Boolean> = mutableStateOf(false)

    private var _subjects: MutableState<List<Subject>> = mutableStateOf(listOf())
    val subjects: State<List<Subject>> = _subjects

    private var _subjectList = listOf<Subject>()

    private var _themeType: MutableState<MainActivity.ThemeType> = mutableStateOf(MainActivity.ThemeType.BEGINNER)
    val themeType: State<MainActivity.ThemeType> = _themeType

    init{
        _subjectList = (1..4).map{ i ->
            Subject("Subject $i", "")
        }
        _subjects.value = getSubjects()
        //_themeType.value = getTheme()
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

    fun subtractPoint() {
        numCorrect.value--
    }

    fun setFinalAnswer(fa: Boolean) {
        finalAnswer.value = fa
    }

    fun getFinalAnswer() : Boolean {
        return finalAnswer.value
    }

    fun getQuizResult(): Int {
        return numCorrect.value
    }

    fun selectionMade(){
        optionSelected.value = true
    }

    fun deselect(){
        optionSelected.value = false
    }

    fun isSelectionMade() : Boolean {
        return optionSelected.value
    }

    fun resetAnswerSelection() {
        optionSelected.value = false
    }

    fun reset() {
        question_Num.value = 1
        progress.value = 0.0F
        submitSelected.value = false
    }

    fun resetNumCorrect(){
        numCorrect.value = 0
    }

    fun getTheme(): MainActivity.ThemeType{
        return themeType.value
    }

    fun getSubmitSelection() : Boolean {
        return submitSelected.value
    }

    fun setSubmitSelection() {
        submitSelected.value = !submitSelected.value
    }

    //for app theme changes
    fun isBeginnerDifficultyClicked(): Boolean {
        beginnerDifficultyClick.value = !beginnerDifficultyClick.value
        intermediateDifficultyClick.value
        advancedDifficultyClick.value
        return beginnerDifficultyClick.value
    }

    fun isIntermediateDifficultyClicked(): Boolean {
        intermediateDifficultyClick.value = !intermediateDifficultyClick.value
        beginnerDifficultyClick.value
        advancedDifficultyClick.value
        return intermediateDifficultyClick.value
    }

    fun isAdvancedDifficultyClicked(): Boolean{
        advancedDifficultyClick.value = !advancedDifficultyClick.value
        beginnerDifficultyClick.value
        intermediateDifficultyClick.value
        return advancedDifficultyClick.value
    }

    private fun getSubjects(): List<Subject> {
        return _subjectList
    }






//    private val _questions: MutableState<List<Question>> = mutableStateOf(listOf())
//
//    private lateinit var _repository: IQuizRepository
//    private val questionFetcher = QuestionFetcher()
//    private val selectQuestion: State<Question?>
//    private val question : MutableState<Question?>
//
//    init {
//        viewModelScope.launch {
//            val questions = questionFetcher.fetchQuestion()
//            _repository = QuizDatabaseRepository(getApplication(), questions)
//
//            questions.forEach{ question -> _repository.getQuestions()}
//        }
//        question = mutableStateOf(null)
//        selectQuestion = question
//    }
//
//    fun getQuestion(): List<Question?> {
//        return questions
//    }


}