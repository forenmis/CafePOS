package com.example.data.database

import android.content.Context
import androidx.room.Room
import com.example.data.database.entity.MenuCategoriesWithMenuItems
import com.example.data.database.entity.MenuCategoryDB
import com.example.data.database.entity.MenuItemDB
import com.example.data.database.entity.MenuPortionDB

internal class DatabaseManagerImpl(context: Context) : DatabaseManager {
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "awesomenu"
    ).build()

    override suspend fun insertCategory(category: MenuCategoryDB): Long {
        return database.categoryDao().insertCategory(category)
    }

    override suspend fun getAllCategories(): List<MenuCategoryDB> {
        return database.categoryDao().getAllCategories()
    }

    override suspend fun categoryCount(): Int {
        return database.categoryDao().categoryCount()
    }

    override suspend fun getCategoryById(id: Long): MenuCategoryDB {
        return database.categoryDao().getCategoryById(id)
    }

    override suspend fun insertItem(menuItemDB: MenuItemDB): Long {
        return database.itemMenuDao().insertItem(menuItemDB)
    }

    override suspend fun getAllItems(): List<MenuItemDB> {
        return database.itemMenuDao().getAllItems()
    }

    override suspend fun itemCount(): Int {
        return database.itemMenuDao().itemCount()
    }

    override suspend fun getItemsByCategory(categoryId: Long): List<MenuItemDB> {
        return database.itemMenuDao().getItemsByCategory(categoryId)
    }

    override suspend fun insertPortion(portionDB: MenuPortionDB): Long {
        return database.portionDao().insertPortion(portionDB)
    }

    override suspend fun getAllPortions(): List<MenuPortionDB> {
        return database.portionDao().getAllPortions()
    }

    override suspend fun portionCount(): Int {
        return database.portionDao().portionCount()
    }

    override suspend fun getPortionById(id: Long): MenuPortionDB {
        return database.portionDao().getPortionById(id)
    }

    override suspend fun getMenuCategoriesWithMenuItems(): List<MenuCategoriesWithMenuItems> {
        return database.categoryDao().getMenuCategoriesWithMenuItems()
    }

    override suspend fun saveMenuItem(itemDB: MenuItemDB) {
        return database.itemMenuDao().saveMenuItem(itemDB)
    }
}