package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.database.entity.MenuCategoriesWithMenuItems
import com.example.data.database.entity.MenuCategoryDB

@Dao
interface MenuCategoryDao {

    @Insert
    suspend fun insertCategory(categoryDB: MenuCategoryDB)

    @Query("SELECT * FROM menu")
    suspend fun getAllCategories(): List<MenuCategoryDB>

    @Query("SELECT COUNT(*) FROM menu")
    suspend fun categoryCount(): Int

    @Query("SELECT * FROM menu")
    @Transaction
    suspend fun getMenuCategoriesWithMenuItems(): List<MenuCategoriesWithMenuItems>
    @Query("SELECT * FROM menu WHERE id = :id")
    suspend fun getCategoryById(id : Long) : MenuCategoryDB
}