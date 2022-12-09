package com.example.queryapp.database

import com.example.queryapp.database.IQuizRepository
import com.example.queryapp.database.Question
import com.example.queryapp.database.QuestionFetcher
import com.example.queryapp.database.QuizDatabaseRepository
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
    val questions: State<List<Question>> = _questions

    private lateinit var _repository: IQuizRepository
    private val questionFetcher = QuestionFetcher(getApplication())

    init {
        viewModelScope.launch {
            _repository = QuizDatabaseRepository(getApplication())
            try {
                _questions.value = questionFetcher.fetchQuestion()
                Log.d("Questions", questions.toString())
                //questions.forEach{}
            }catch (e: Exception){
                Toast.makeText(app, e.message, Toast.LENGTH_SHORT).show()
            }
            //_questions.value = _repository.getQuestions()
        }
        Log.e("questions", _questions.value[1].question)
    }

    fun getQuestion(idx: Int) : String {
        Log.e("questions", _questions.value[0].question)
        return _questions.value[idx].question
    }

}
