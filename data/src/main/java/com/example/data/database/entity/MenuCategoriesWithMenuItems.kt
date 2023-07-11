package com.example.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MenuCategoriesWithMenuItems(
    @Embedded
    val category: MenuCategoryDB,
    @Relation(
        entity = MenuItemDB::class,
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val items: List<MenuItemWithPortion>
)

data class MenuItemWithPortion(
    @Embedded
    val item: MenuItemDB,
    @Relation(
        parentColumn = "portionId",
        entityColumn = "id",
    )
    val portion: MenuPortionDB
)