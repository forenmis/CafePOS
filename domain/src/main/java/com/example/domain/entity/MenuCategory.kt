package com.example.domain.entity

data class MenuCategory(//самая большая
    val id: Long,
    val name: String,
    val icon: MenuCategoryIcon,
    val menuItems : List<MenuItem>
)

sealed interface MenuCategoryIcon {
    object Coffee : MenuCategoryIcon
    object Cake : MenuCategoryIcon
    object Other : MenuCategoryIcon
}
