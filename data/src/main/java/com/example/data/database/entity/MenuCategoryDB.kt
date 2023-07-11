package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuCategoryDB(//самая большая
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val menuCategoryIcon: String
)
