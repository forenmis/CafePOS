package com.example.domain.entity

data class MenuCategory(//самая большая
    val id: Long,
    val name: String,
    val icon: MenuCategoryIcon,
    val menuItems: List<MenuItem>
)

sealed class MenuCategoryIcon(val name: String) {
    object Coffee : MenuCategoryIcon("Coffee")
    object Cake : MenuCategoryIcon("Cake")
    object Other : MenuCategoryIcon("Other")

    companion object {
        fun getCategoryIconByName(name: String?): MenuCategoryIcon {
            return when (name) {
                Coffee.name -> Coffee
                Cake.name -> Cake
                Other.name -> Other
                else -> throw IllegalArgumentException("getCategoryIconByName")
            }
        }
    }
}
