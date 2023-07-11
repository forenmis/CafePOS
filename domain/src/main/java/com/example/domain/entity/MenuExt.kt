package com.example.domain.entity

import com.example.data.database.entity.MenuCategoryDB
import com.example.data.database.entity.MenuItemDB
import com.example.data.database.entity.MenuPortionDB

fun PortionType.toMenuPortionDB(): MenuPortionDB {
    return MenuPortionDB(
        id = this.id,
        name = this.name,
        typePortion = this.shortName
    )
}

fun MenuPortionDB.toMenuPortion(): PortionType {
    return PortionType(
        id = this.id,
        name = this.name,
        shortName = this.typePortion
    )
}

fun MenuItem.toMenuItemDB(): MenuItemDB {
    return MenuItemDB(
        id = this.id,
        name = this.name,
        image = this.image,
        price = this.price,
        portionId = this.portionType.id,
        portionSize = this.portionSize,
        categoryId = this.categoryId
    )
}

fun MenuItemDB.toMenuItem(portionDB: MenuPortionDB): MenuItem {
    return MenuItem(
        id = this.id,
        name = this.name,
        image = this.image,
        price = this.price,
        portionType = portionDB.toMenuPortion(),
        portionSize = this.portionSize,
        categoryId = this.categoryId
    )
}

fun MenuCategoryDB.toMenuCategory(menuItems: List<MenuItem>): MenuCategory {
    return MenuCategory(
        id = this.id,
        name = this.name,
        icon = MenuCategoryIcon.getCategoryIconByName(this.menuCategoryIcon),
        menuItems = menuItems
    )
}

fun MenuCategory.toMenuCategoryDB(): MenuCategoryDB {
    return MenuCategoryDB(
        id = this.id,
        name = this.name,
        menuCategoryIcon = icon.name
    )
}

