package com.example.presentation.screens.home.create.type_portion__bottom_sheet

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PortionType
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectTypeViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {

    val portionTypesFlow = MutableStateFlow<List<PortionType>>(emptyList())

    fun loadPortionTypes() = viewModelScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
            val list = menuRepository.getAllPortionTypes()
            portionTypesFlow.emit(list)
        }
    }
}