package com.example.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.auth.AuthRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val authRepository: AuthRepository) : BaseViewModel() {
    val userAuthFlow = MutableSharedFlow<Boolean>()

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch(coroutineExceptionHandler) {
        processFlow.emit(true)
        withContext(Dispatchers.IO) {
            authRepository.login(username, password)
            delay(3_000)
        }
        processFlow.emit(false)
        userAuthFlow.emit(true)
    }
}