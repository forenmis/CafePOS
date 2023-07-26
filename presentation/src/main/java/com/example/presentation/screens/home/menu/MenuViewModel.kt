package com.example.presentation.screens.home.menu

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {
    val menuFlow = MutableStateFlow<List<MenuCategory>>(emptyList())

    fun getMenu() {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getMenu()
                menuFlow.emit(list)
            }
        }
    }

}