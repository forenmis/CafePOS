package com.example.presentation.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.auth.AuthRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val authRepository: AuthRepository) : ViewModel() {
    val userAuthFlow = MutableSharedFlow<Boolean>()
    val exceptionFlow = MutableSharedFlow<Throwable>()

    fun registration(username: String, email: String, password: String) = viewModelScope.launch {
        (CoroutineExceptionHandler { _, throwable -> exceptionFlow.tryEmit(throwable) })
        val token = withContext(Dispatchers.IO) {
            authRepository.registration(username, email, password)
        }
        userAuthFlow.emit(token.isNotEmpty())
    }
}