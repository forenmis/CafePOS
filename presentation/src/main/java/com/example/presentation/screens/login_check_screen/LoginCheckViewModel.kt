package com.example.presentation.screens.login_check_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.auth.AuthRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LoginCheckViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    val screenFLow = MutableSharedFlow<Action>()

    init {
        checkScreen()
    }

    private fun checkScreen() = viewModelScope.launch(Dispatchers.IO) {
        val hasUser = authRepository.checkToken()
        screenFLow.emit(if (hasUser) Action.ToHome else Action.ToLogin)
    }
}

sealed interface Action {
    object ToLogin : Action
    object ToHome : Action
}