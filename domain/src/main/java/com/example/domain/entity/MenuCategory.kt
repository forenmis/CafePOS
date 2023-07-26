package com.example.domain.entity

data class MenuCategory(//самая большая
    val id: Long = 0,
    val name: String,
    val icon: MenuCategoryIcon,
    val menuItems: List<MenuItem> = emptyList()
)

sealed class MenuCategoryIcon(val name: String) {
    object Coffee : MenuCategoryIcon("Coffee")
    object Cake : MenuCategoryIcon("Cake")
    object Bakery : MenuCategoryIcon("Bakery")
    object Blender : MenuCategoryIcon("Blender")
    object FastFood : MenuCategoryIcon("FastFood")
    object HomeFood : MenuCategoryIcon("HomeFood")
    object Tea : MenuCategoryIcon("Tea")
    object Other : MenuCategoryIcon("Other")

    companion object {
        fun getCategoryIconByName(name: String?): MenuCategoryIcon {
            return when (name) {
                Coffee.name -> Coffee
                Cake.name -> Cake
                Bakery.name -> Bakery
                Blender.name -> Blender
                FastFood.name -> FastFood
                HomeFood.name -> HomeFood
                Tea.name -> Tea
                Other.name -> Other
                else -> throw IllegalArgumentException("getCategoryIconByName")
            }
        }
    }
}
