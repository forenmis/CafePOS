package com.example.presentation.screens.home.menu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.domain.entity.MenuCategory
import com.example.domain.entity.MenuCategoryIcon
import com.example.presentation.R
import com.example.presentation.databinding.ItemMenuCategoryBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.VH>() {
    private var menu = emptyList<MenuCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemMenuCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = menu.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val category = menu[position]
        holder.feelMenu(category)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<MenuCategory>) {
        menu = newItems
        notifyDataSetChanged()
    }


    class VH(private val binding: ItemMenuCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val menuItemAdapter = MenuItemAdapter()

        init {
            binding.rvCategoryItems.layoutManager =
                LinearLayoutManager(itemView.context, HORIZONTAL, false)
            binding.rvCategoryItems.adapter = menuItemAdapter
        }

        fun feelMenu(menuCategory: MenuCategory) {
            binding.tvCategory.text = menuCategory.name
            defineIcon(menuCategory)
            menuItemAdapter.updateItems(menuCategory.menuItems)
        }

        private fun defineIcon(menuCategory: MenuCategory) {
            val icon = when (menuCategory.icon) {
                MenuCategoryIcon.Cake -> R.drawable.ic_cake
                MenuCategoryIcon.Coffee -> R.drawable.ic_coffee
                MenuCategoryIcon.Other -> R.drawable.ic_something
            }
            binding.tvCategory.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        }
    }
}