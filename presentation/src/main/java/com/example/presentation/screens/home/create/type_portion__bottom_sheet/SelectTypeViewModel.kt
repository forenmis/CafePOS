package com.example.presentation.screens.home.create.type_portion__bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PortionType
import com.example.domain.repository.menu.MenuRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectTypeViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val portionTypesFlow = MutableStateFlow<List<PortionType>>(emptyList())
    val exceptionFlow = MutableSharedFlow<Throwable>()

    fun loadPortionTypes() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getAllPortionTypes()
                portionTypesFlow.emit(list)
            }
        }
    }
}