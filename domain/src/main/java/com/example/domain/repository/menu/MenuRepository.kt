package com.example.domain.repository.menu

import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuItem
import com.example.domain.entity.PortionType

interface MenuRepository {
    suspend fun initMenu()
    suspend fun getMenu(): List<MenuCategory>

    suspend fun getPortionById(id : Long): PortionType

    suspend fun getCategoryById(id : Long) : MenuCategory

    suspend fun getAllPortionTypes() : List<PortionType>

    suspend fun saveMenuItem(item : MenuItem)
}