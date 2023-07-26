package com.example.presentation.screens.registration

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.auth.AuthRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val authRepository: AuthRepository) : BaseViewModel() {
    val userAuthFlow = MutableSharedFlow<Boolean>()

    fun registration(
        username: String,
        email: String,
        password: String
    ) = viewModelScope.launch(coroutineExceptionHandler) {
        processFlow.emit(true)
        withContext(Dispatchers.IO) {
            authRepository.registration(username, email, password)
            delay(3_000)
        }
        processFlow.emit(false)
        userAuthFlow.emit(true)
    }
}