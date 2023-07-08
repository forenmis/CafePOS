package com.example.domain.repository.menu

import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuCategoryIcon
import com.example.domain.entity.MenuItem
import com.example.domain.entity.MenuPortion

internal class MenuRepositoryImpl : MenuRepository {
    override suspend fun getMenu(): List<MenuCategory> {
        val menuPortionMl = MenuPortion(
            id = 1L,
            name = "millilitres",
            typePortion = "ml"
        )
        val menuPortionGramm = MenuPortion(
            id = 2L,
            name = "gramm",
            typePortion = "g"
        )
        return listOf(
            MenuCategory(
                id = 1L,
                name = "Кава",
                icon = MenuCategoryIcon.Coffee,
                menuItems = listOf(
                    MenuItem(
                        id = 1L,
                        name = "Cappuch",
                        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiLCUM2fMFEcTDYSFX4XzbQKN0K2OZO3_ipLgsW-dIsSY5LBE5yb8hu9x-pw-NAwmFn2c&usqp=CAU",
                        price = 10.50,
                        portion = menuPortionMl,
                        portionSize = 240,
                        categoryId = 1L
                    ),
                    MenuItem(
                        id = 2L,
                        name = "Coffee",
                        image = "https://images.absolutdrinks.com/drink-images/Raw/Kahlua/8317f0a2-a13e-4eeb-954a-ae1d603537a6.jpg",
                        price = 20.50,
                        portion = menuPortionMl,
                        portionSize = 180,
                        categoryId = 1L
                    )
                )
            ),
            MenuCategory(
                id = 2L,
                name = "Cake",
                icon = MenuCategoryIcon.Cake,
                menuItems = listOf(
                    MenuItem(
                        id = 3L,
                        name = "Cheesecake",
                        image = "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/rainbow_cake_20402_16x9.jpg",
                        price = 30.50,
                        portion = menuPortionGramm,
                        portionSize = 300,
                        categoryId = 1L
                    ),
                    MenuItem(
                        id = 4L,
                        name = "Napoleon",
                        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTye8M0706guLdTGqd6xTALI9SFh87-MEYU5A&usqp=CAU",
                        price = 5.50,
                        portion = menuPortionGramm,
                        portionSize = 100,
                        categoryId = 1L
                    )
                )
            )
        )
    }
}