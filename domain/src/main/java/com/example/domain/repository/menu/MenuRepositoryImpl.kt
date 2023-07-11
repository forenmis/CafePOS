package com.example.domain.repository.menu

import com.example.data.database.DatabaseManager
import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuCategoryIcon
import com.example.domain.entity.MenuItem
import com.example.domain.entity.PortionType
import com.example.domain.entity.toMenuCategory
import com.example.domain.entity.toMenuCategoryDB
import com.example.domain.entity.toMenuItem
import com.example.domain.entity.toMenuItemDB
import com.example.domain.entity.toMenuPortionDB

internal class MenuRepositoryImpl(private val databaseManager: DatabaseManager) : MenuRepository {
    override suspend fun initMenu() {
        if (databaseManager.categoryCount() == 0) {
            val coffeeCategory = MenuCategory(
                id = 1L,
                name = "Кава",
                icon = MenuCategoryIcon.Coffee,
                menuItems = emptyList()
            )
            val cakeCategory = MenuCategory(
                id = 2L,
                name = "Cake",
                icon = MenuCategoryIcon.Cake,
                menuItems = emptyList()
            )
            val otherCategory = MenuCategory(
                id = 3L,
                name = "Other",
                icon = MenuCategoryIcon.Other,
                menuItems = emptyList()
            )
            databaseManager.insertCategory(coffeeCategory.toMenuCategoryDB())
            databaseManager.insertCategory(cakeCategory.toMenuCategoryDB())
            databaseManager.insertCategory(otherCategory.toMenuCategoryDB())

            val portionG = PortionType(1L, "g", "gram")
            val portionMl = PortionType(2L, "ml", "millilitres")

            databaseManager.insertPortion(portionG.toMenuPortionDB())
            databaseManager.insertPortion(portionMl.toMenuPortionDB())

            val cappuccino = MenuItem(
                id = 1L,
                name = "Cappuch",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiLCUM2fMFEcTDYSFX4XzbQKN0K2OZO3_ipLgsW-dIsSY5LBE5yb8hu9x-pw-NAwmFn2c&usqp=CAU",
                price = 10.50,
                portionType = portionMl,
                portionSize = 240,
                categoryId = coffeeCategory.id
            )
            val coffee = MenuItem(
                id = 2L,
                name = "Coffee",
                image = "https://images.absolutdrinks.com/drink-images/Raw/Kahlua/8317f0a2-a13e-4eeb-954a-ae1d603537a6.jpg",
                price = 20.50,
                portionType = portionMl,
                portionSize = 180,
                categoryId = coffeeCategory.id
            )
            val cheesecake = MenuItem(
                id = 3L,
                name = "Cheesecake",
                image = "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/rainbow_cake_20402_16x9.jpg",
                price = 30.50,
                portionType = portionG,
                portionSize = 300,
                categoryId = cakeCategory.id
            )
            val napoleon = MenuItem(
                id = 4L,
                name = "Napoleon",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTye8M0706guLdTGqd6xTALI9SFh87-MEYU5A&usqp=CAU",
                price = 5.50,
                portionType = portionG,
                portionSize = 100,
                categoryId = cakeCategory.id
            )
            //вставить все
            //покл рум, вставить
            //прверить вставку
            databaseManager.insertItem(cappuccino.toMenuItemDB())
            databaseManager.insertItem(coffee.toMenuItemDB())
            databaseManager.insertItem(cheesecake.toMenuItemDB())
            databaseManager.insertItem(napoleon.toMenuItemDB())

        }
    }


    override suspend fun getMenu(): List<MenuCategory> {
        val menuCategoriesWithMenuItems = databaseManager.getMenuCategoriesWithMenuItems()
        return menuCategoriesWithMenuItems.map { menuCategoryWithMenuItems ->
            val menuItems = menuCategoryWithMenuItems.items.map { menuItemDB ->
                menuItemDB.item.toMenuItem(menuItemDB.portion)
            }
            menuCategoryWithMenuItems.category.toMenuCategory(menuItems)
        }
    }
}