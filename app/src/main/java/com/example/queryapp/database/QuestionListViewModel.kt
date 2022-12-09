package com.example.queryapp.database

import android.app.Application
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
                    val questions = questionFetcher.fetchQuestion()
                    questions.forEach{ _questions.value }
                }catch (e: Exception){
                    Toast.makeText(app, e.message, Toast.LENGTH_SHORT).show()
                }
               _questions.value = _repository.getQuestions()
            }
        }
    }