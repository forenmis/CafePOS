package com.example.presentation.screens.home.create.category_bottom_sheet

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

class BottomSheetViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    val categoryFlow = MutableStateFlow<List<MenuCategory>>(emptyList())
    val exceptionFlow = MutableSharedFlow<Throwable>()



    fun loadCategories() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getMenu()
                categoryFlow.emit(list)
            }
        }
    }




}