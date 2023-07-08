package com.example.presentation.screens.home.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.repository.menu.MenuRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(val menuRepository: MenuRepository) : ViewModel() {
    val menuFlow = MutableStateFlow<List<MenuCategory>>(emptyList())
    val exceptionFlow = MutableSharedFlow<Throwable>()

    fun getMenu() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getMenu()
                menuFlow.emit(list)
            }
        }
    }

}