package com.example.presentation.screens.home.create

import com.example.presentation.R


sealed class Pages(val position: Int, val menuId: Int) {
    object Orders : Pages(0, R.id.menuFragmentOrder)
    object Menu : Pages(1, R.id.menuFragmentMenu)
    object Settings : Pages(2, R.id.menuFragmentSettings)


    companion object {
        fun findPageByPosition(position: Int): Pages {
            return when (position) {
                Orders.position -> Orders
                Menu.position -> Menu
                Settings.position -> Settings
                else -> throw IllegalArgumentException()
            }
        }

        fun findPageByMenuId(menuId: Int): Pages {
            return when (menuId) {
                Orders.menuId -> Orders
                Menu.menuId -> Menu
                Settings.menuId -> Settings
                else -> throw IllegalArgumentException()
            }
        }
    }
}
