package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_type_portion

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

class CreateTypePortionViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val exceptionFlow = MutableSharedFlow<Throwable>()
    private val nameFlow = MutableStateFlow<String?>(null)
    private val shortNameFlow = MutableStateFlow<String?>(null)
    val completeFillFlow = MutableStateFlow(false)

    fun savePortionType() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            withContext(Dispatchers.IO) {
                val portionType = PortionType(
                    id = 0,
                    name = nameFlow.value ?: throw Throwable("name is missing"),
                    shortName = shortNameFlow.value ?: throw Throwable("shortName is missing")
                )
                menuRepository.saveTypePortion(portionType)
            }
        }
    }

    private fun checkFields() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            completeFillFlow.tryEmit(nameFlow.value != null && shortNameFlow.value != null)
        }
    }

    fun saveName(name: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            nameFlow.emit(name)
            checkFields()
        }
    }

    fun saveShortName(shortname: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) {
            shortNameFlow.emit(shortname)
            checkFields()
        }
    }
}