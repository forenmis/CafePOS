package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_type_portion

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PortionType
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateTypePortionViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {

    private val nameFlow = MutableStateFlow("")
    private val shortNameFlow = MutableStateFlow("")

    val completeFillFlow = combine(nameFlow, shortNameFlow) { name, shortName ->
        return@combine name.isNotEmpty() && shortName.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = false)

    fun savePortionType() = viewModelScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
            val portionType = PortionType(
                id = 0,
                name = nameFlow.value,
                shortName = shortNameFlow.value
            )
            menuRepository.saveTypePortion(portionType)
        }
    }

    fun saveName(name: String) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) { nameFlow.emit(name) }

    fun saveShortName(shortname: String) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            shortNameFlow.emit(
                shortname
            )
        }
}