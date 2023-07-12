package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.MenuPortionDB

@Dao
interface PortionDao {

    @Insert
    suspend fun insertPortion(portionDB: MenuPortionDB): Long

    @Query("SELECT * FROM portionTypes")
    suspend fun getAllPortions(): List<MenuPortionDB>

    @Query("SELECT COUNT(*) FROM portionTypes")
    suspend fun portionCount(): Int

    @Query("SELECT * FROM portionTypes WHERE id = :id")
    suspend fun getPortionById(id : Long): MenuPortionDB
}