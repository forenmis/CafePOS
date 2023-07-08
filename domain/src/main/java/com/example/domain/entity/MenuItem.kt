package com.example.domain.entity

data class MenuItem(
    val id : Long,
    val name : String,
    val image : String,
    val price : Double,
    val portion: MenuPortion,
    val portionSize : Int,
    val categoryId : Long
)
