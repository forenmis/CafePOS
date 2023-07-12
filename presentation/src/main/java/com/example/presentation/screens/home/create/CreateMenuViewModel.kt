package com.example.presentation.screens.home.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.entity.PortionType
import com.example.domain.repository.menu.MenuRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateMenuViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val categoryFlow = MutableSharedFlow<MenuCategory>()
    val exceptionFlow = MutableSharedFlow<Throwable>()
    val chooseCategoryFlow = MutableSharedFlow<MenuCategory>()

    private var menuCategory: MenuCategory? = null
    private var typePortion: PortionType? = null

    fun loadCategoryById(id: Long) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            withContext(Dispatchers.IO) {
                val category = menuRepository.getCategoryById(id)
                menuCategory = category
                chooseCategoryFlow.emit(category)
            }
        }
    }

    fun saveTypePortion(portionType: PortionType) {
        typePortion = portionType
    }


}