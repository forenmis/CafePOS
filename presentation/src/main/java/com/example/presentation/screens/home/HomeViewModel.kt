package com.example.presentation.screens.home

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {

    val menuToCreateScreenFlow = MutableSharedFlow<Boolean>()
    val homeToLoginScreenFlow = MutableSharedFlow<Boolean>()

    init {
        viewModelScope.launch {
            menuRepository.initMenu()
        }
    }

    fun toCreateScreen() = viewModelScope.launch {
        menuToCreateScreenFlow.emit(true)
    }

    fun toLoginScreen() = viewModelScope.launch {
        homeToLoginScreenFlow.emit(true)
    }
}