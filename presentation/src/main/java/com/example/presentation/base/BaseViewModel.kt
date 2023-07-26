package com.example.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel(){
    val processFlow = MutableStateFlow(false)
    val exceptionFlow = MutableSharedFlow<Throwable>()

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        processFlow.value = false
        exceptionFlow.tryEmit(throwable)
    }

}