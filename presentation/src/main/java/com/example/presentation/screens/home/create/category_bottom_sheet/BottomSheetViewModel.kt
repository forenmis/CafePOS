package com.example.presentation.screens.home.create.category_bottom_sheet

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomSheetViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {
    val categoryFlow = MutableStateFlow<List<MenuCategory>>(emptyList())

    fun loadCategories() {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getMenu()
                categoryFlow.emit(list)
            }
        }
    }
}