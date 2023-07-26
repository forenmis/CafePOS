package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_category

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MenuCategory
import com.example.domain.repository.menu.MenuRepository
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.utils.getCategoryIconByRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateCategoryViewModel(private val menuRepository: MenuRepository) : BaseViewModel() {

    val imagesForCategoryFlow = MutableStateFlow(
        listOf(
            ImageCategory(R.drawable.ic_type_bakery),
            ImageCategory(R.drawable.ic_type_blender),
            ImageCategory(R.drawable.ic_type_cake),
            ImageCategory(R.drawable.ic_type_coffee),
            ImageCategory(R.drawable.ic_type_fastfood),
            ImageCategory(R.drawable.ic_type_homefood),
            ImageCategory(R.drawable.ic_type_tea)
        )
    )
    private val nameFlow = MutableStateFlow("")
    val completeFillFlow = combine(nameFlow, imagesForCategoryFlow) { name, imagesForCategories ->
        return@combine name.isNotEmpty() && imagesForCategories.firstOrNull { it.isChanged } != null
    }.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = false)


    fun selectImageCategory(imageCategory: ImageCategory) = viewModelScope.launch {
        val newList = imagesForCategoryFlow.value.map {
            it.copy(isChanged = if (it == imageCategory) !it.isChanged else false)
        }
        imagesForCategoryFlow.emit(newList)
    }

    fun editName(text: String) = viewModelScope.launch { nameFlow.emit(text) }

    fun saveMenuCategory() = viewModelScope.launch {
        val icon = imagesForCategoryFlow.value.first { it.isChanged }
        menuRepository.saveCategory(
            MenuCategory(
                id = 0,
                name = nameFlow.value,
                icon = getCategoryIconByRes(icon.res)
            )
        )
    }
}

data class ImageCategory(
    val res: Int,
    val isChanged: Boolean = false
)
