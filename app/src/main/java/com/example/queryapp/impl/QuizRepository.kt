package com.example.queryapp.impl

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.queryapp.MainActivity
import com.example.queryapp.database.QuestionListViewModel
import com.example.queryapp.pages.SubjectSelection.Subject


class QuizRepository(app: Application) : AndroidViewModel(app) {
    private val questionNum: MutableState<Int> = mutableStateOf(1)

    private val progress: MutableState<Float> = mutableStateOf(0.0F)

    private val numCorrect: MutableState<Int> = mutableStateOf(0)

    private val selection: MutableState<String> = mutableStateOf("Z")

    private val showAnswers: MutableState<Boolean> = mutableStateOf(false)

    private val submitSelected: MutableState<Boolean> = mutableStateOf(false)

    private val finalAnswer: MutableState<Boolean> = mutableStateOf(false)

    private val beginnerDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val intermediateDifficultyClick: MutableState<Boolean> = mutableStateOf(false)
    private val advancedDifficultyClick: MutableState<Boolean> = mutableStateOf(false)

    private var _subjects: MutableState<List<Subject>> = mutableStateOf(listOf())

    private var _subjectList = listOf<Subject>()

    private var _themeType: MutableState<MainActivity.ThemeType> = mutableStateOf(MainActivity.ThemeType.BEGINNER)
    val themeType: State<MainActivity.ThemeType> = _themeType

    private var questionVM: QuestionListViewModel? = null

    init{
        _subjectList = (1..4).map{ i ->
            Subject("Subject $i", "")
        }
        _subjects.value = getSubjects()
    }


    fun getQuestionNum(): Int {
        return questionNum.value
    }

    fun nextQuestion() {
        questionNum.value++
        progress.value = 0.1F*(questionNum.value - 1)
        questionVM?.nextQuestion()
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

    fun selectionMade(letter: String){
        selection.value = letter
    }


    fun currentSelection() : String {
        return selection.value
    }

    fun resetAnswerSelection() {
        selection.value = "Z"
    }

    fun reset() {
        questionNum.value = 1
        progress.value = 0.0F
        submitSelected.value = false
        questionVM?.reset()
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

    fun displayAnswers() {
        showAnswers.value = true
    }

    fun hideAnswers() {
        showAnswers.value = false
    }

    fun getShowAnswersValue() : Boolean {
        return showAnswers.value
    }

    fun setBeginnerDifficulty() {
        beginnerDifficultyClick.value = true
    }

    fun setIntermediateDifficulty() {
        intermediateDifficultyClick.value = true
    }

    fun setAdvancedDifficulty() {
        advancedDifficultyClick.value = true
    }

    private fun getSubjects(): List<Subject> {
        return _subjectList
    }

    fun setQuestionVM(ques: QuestionListViewModel) {
        questionVM = ques
        var difficulty = 0
        if(beginnerDifficultyClick.value) {
            difficulty = 1
        }
        if(intermediateDifficultyClick.value){
            difficulty = 2
        }
        if(advancedDifficultyClick.value){
            difficulty = 3
        }
        ques.getFirstQuestion(difficulty)
    }






}