package com.example.queryapp.database

import android.app.Application
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
        private val questionFetcher = QuestionFetcher()

        init {
            viewModelScope.launch {
                val questions = questionFetcher.fetchQuestion()
                _repository = QuizDatabaseRepository(getApplication(), questions)
                _questions.value = _repository.getQuestions()

                questions.forEach{ question -> _repository.getQuestions()}

            }
        }
    }