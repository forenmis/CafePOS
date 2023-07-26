package com.example.presentation.screens.home.settings

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.auth.AuthRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    val screenFLow = MutableSharedFlow<Boolean>()

    fun exit() = viewModelScope.launch {
        authRepository.clearToken()
        screenFLow.emit(true)
    }
}