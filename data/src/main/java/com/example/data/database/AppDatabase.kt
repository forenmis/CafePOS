package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.ItemMenuDao
import com.example.data.database.dao.MenuCategoryDao
import com.example.data.database.dao.PortionDao
import com.example.data.database.entity.MenuCategoryDB
import com.example.data.database.entity.MenuItemDB
import com.example.data.database.entity.MenuPortionDB

@Database(
    entities = [
        MenuCategoryDB::class,
        MenuItemDB::class,
        MenuPortionDB::class
    ], version = 2
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): MenuCategoryDao
    abstract fun itemMenuDao(): ItemMenuDao
    abstract fun portionDao(): PortionDao

}