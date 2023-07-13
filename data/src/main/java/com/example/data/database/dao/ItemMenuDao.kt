package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.MenuItemDB

@Dao
interface ItemMenuDao {

    @Insert
    suspend fun insertItem(menuItemDB: MenuItemDB): Long

    @Query("SELECT * FROM menuItem")
    suspend fun getAllItems(): List<MenuItemDB>

    @Query("SELECT COUNT(*) FROM menuItem")
    suspend fun itemCount(): Int

    @Query("SELECT * FROM menuItem WHERE categoryId = :categoryId")
    suspend fun getItemsByCategory(categoryId: Long): List<MenuItemDB>

    @Insert
    suspend fun saveMenuItem(itemDB: MenuItemDB)

}