package com.example.domain.repository.menu

import com.example.domain.entity.MenuCategory

interface MenuRepository {
    suspend fun getMenu(): List<MenuCategory>
}