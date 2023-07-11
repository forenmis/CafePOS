package com.example.presentation.screens.home.menu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.MenuItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemMenuBinding
import com.example.presentation.utils.setImage

class MenuItemAdapter : RecyclerView.Adapter<MenuItemAdapter.VH>() {

    private var menuItems = emptyList<MenuItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<MenuItem>) {
        menuItems = newItems
        notifyDataSetChanged()
    }

    class VH(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelItemNote(item: MenuItem) {
            with(binding) {
                tvItemName.text = item.name
                tvPrice.text = item.price.toString()
                ivItemMenuImage.setImage(item.image)
                tvPortionSize.text = tvPortionSize.context.getString(
                    R.string.pattern_portion_size,
                    item.portionSize,
                    item.portionType.shortName
                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = menuItems.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = menuItems[position]
        holder.feelItemNote(item)
    }
}