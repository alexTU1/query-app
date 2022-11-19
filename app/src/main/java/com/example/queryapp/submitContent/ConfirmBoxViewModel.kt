package com.example.queryapp.submitContent

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfirmBoxViewModel: ViewModel() {
    private val _showConfirmBox = MutableStateFlow(false)
    val showConfirmBox: StateFlow<Boolean>  = _showConfirmBox.asStateFlow()

    fun onDismiss(){
        _showConfirmBox.value = false
    }
}