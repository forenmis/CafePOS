package com.example.presentation.screens.home.create

import android.app.Application
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuItem
import com.example.domain.entity.PortionType
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.base.BaseViewModel
import com.example.presentation.utils.createTemporaryFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class CreateMenuViewModel(
    private val context: Application,
    private val menuRepository: MenuRepository
) : BaseViewModel() {

    val chooseCategoryFlow = MutableSharedFlow<MenuCategory>()
    val pathFlow = MutableStateFlow<String?>(null)
    val savingError = MutableSharedFlow<Boolean>()

    private var menuCategory: MenuCategory? = null
    private var typePortion: PortionType? = null
    private var uri: Uri? = null

    fun loadCategoryById(id: Long) {
        viewModelScope.launch(coroutineExceptionHandler) {
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

    fun createCameraFile(): Uri {
        val file: File = createTemporaryFile(context)
        pathFlow.value = file.absolutePath
        val result = FileProvider.getUriForFile(
            context,
            "com.example.cleanarchitecture.presentation.fileprovider",
            file
        )
        uri = result
        return result
    }


    private fun checkSaving(): Boolean {
        return (typePortion != null && menuCategory != null && pathFlow.value != null)
    }

    fun saveMenuItem(name: String, price: String, portionSize: String) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (checkSaving()) {
                val menuItem = MenuItem(
                    name = name,
                    image = pathFlow.value!!,
                    price = price.toDouble(),
                    portionType = typePortion!!,
                    portionSize = portionSize.toInt(),
                    categoryId = menuCategory!!.id,
                    id = 0
                )
                menuRepository.saveMenuItem(menuItem)
            } else {
                savingError.emit(true)
            }
        }
}