package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portionTypes")
data class MenuPortionDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,//gramm/millilitres
    val typePortion: String//g/ml
)
