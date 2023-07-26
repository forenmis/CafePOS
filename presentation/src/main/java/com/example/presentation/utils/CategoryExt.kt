package com.example.presentation.utils

import androidx.annotation.DrawableRes
import com.example.domain.entity.MenuCategoryIcon
import com.example.presentation.R

@DrawableRes
fun getIconByCategory(menuCategoryIcon: MenuCategoryIcon): Int {
    return when (menuCategoryIcon) {
        MenuCategoryIcon.Coffee -> R.drawable.ic_type_coffee
        MenuCategoryIcon.Cake -> R.drawable.ic_type_cake
        MenuCategoryIcon.Bakery -> R.drawable.ic_type_bakery
        MenuCategoryIcon.Blender -> R.drawable.ic_type_blender
        MenuCategoryIcon.FastFood -> R.drawable.ic_type_fastfood
        MenuCategoryIcon.HomeFood -> R.drawable.ic_type_homefood
        MenuCategoryIcon.Tea -> R.drawable.ic_type_tea
        MenuCategoryIcon.Other -> R.drawable.ic_something
    }
}

fun getCategoryIconByRes(res: Int): MenuCategoryIcon {
    return when (res) {
        R.drawable.ic_type_coffee -> MenuCategoryIcon.Coffee
        R.drawable.ic_type_cake -> MenuCategoryIcon.Cake
        R.drawable.ic_type_bakery -> MenuCategoryIcon.Bakery
        R.drawable.ic_type_blender -> MenuCategoryIcon.Blender
        R.drawable.ic_type_fastfood -> MenuCategoryIcon.FastFood
        R.drawable.ic_type_homefood -> MenuCategoryIcon.HomeFood
        R.drawable.ic_type_tea -> MenuCategoryIcon.Tea
        R.drawable.ic_something -> MenuCategoryIcon.Other
        else -> throw IllegalArgumentException("getCategoryIconByRes")
    }
}