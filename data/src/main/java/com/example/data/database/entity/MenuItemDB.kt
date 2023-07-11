package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menuItem")
data class MenuItemDB(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String,
    val image : String,
    val price : Double,
    val portionId: Long,
    val portionSize : Int,
    val categoryId : Long
)
