package com.example.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.menu.MenuRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val menuToCreateScreenFlow = MutableSharedFlow<Boolean>()

    init {
        viewModelScope.launch {
            menuRepository.initMenu()
        }
    }

    fun toCreateScreen() = viewModelScope.launch{
        menuToCreateScreenFlow.emit(true)
    }
}