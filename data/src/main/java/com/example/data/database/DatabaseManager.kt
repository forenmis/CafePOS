package com.example.data.database

import com.example.data.database.entity.MenuCategoriesWithMenuItems
import com.example.data.database.entity.MenuCategoryDB
import com.example.data.database.entity.MenuItemDB
import com.example.data.database.entity.MenuPortionDB

interface DatabaseManager {

    suspend fun insertCategory(category: MenuCategoryDB): Long

    suspend fun getAllCategories(): List<MenuCategoryDB>

    suspend fun categoryCount(): Int


    suspend fun insertItem(menuItemDB: MenuItemDB): Long

    suspend fun getAllItems(): List<MenuItemDB>

    suspend fun itemCount(): Int

    suspend fun getItemsByCategory(categoryId: Long): List<MenuItemDB>


    suspend fun insertPortion(portionDB: MenuPortionDB): Long

    suspend fun getAllPortions(): List<MenuPortionDB>

    suspend fun portionCount(): Int



    suspend fun getMenuCategoriesWithMenuItems() : List<MenuCategoriesWithMenuItems>

}