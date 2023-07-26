package com.example.presentation.screens.home.create.category_bottom_sheet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuCategoryIcon
import com.example.presentation.R
import com.example.presentation.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.VH>() {

    private var categories = emptyList<MenuCategory>()
    var onCategoryClick: ((MenuCategory) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val category = categories[position]
        holder.feelCategories(category)
        holder.binding.root.setOnClickListener {
            onCategoryClick?.invoke(category)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<MenuCategory>) {
        categories = newItems
        notifyDataSetChanged()
    }

    class VH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun feelCategories(category: MenuCategory) {
            val icon = when (category.icon) {
                MenuCategoryIcon.Cake -> R.drawable.ic_type_cake
                MenuCategoryIcon.Coffee -> R.drawable.ic_type_coffee
                MenuCategoryIcon.Other -> R.drawable.ic_something
                MenuCategoryIcon.Bakery ->  R.drawable.ic_type_bakery
                MenuCategoryIcon.Blender ->  R.drawable.ic_type_blender
                MenuCategoryIcon.FastFood -> R.drawable.ic_type_fastfood
                MenuCategoryIcon.HomeFood -> R.drawable.ic_type_homefood
                MenuCategoryIcon.Tea -> R.drawable.ic_type_tea
            }
            binding.tvCategory.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
            binding.tvCategory.text = category.name

        }
    }
}