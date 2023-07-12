package com.example.presentation.screens.home.create.type_portion__bottom_sheet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PortionType
import com.example.presentation.databinding.ItemCategoryBinding

class PortionAdapter : RecyclerView.Adapter<PortionAdapter.VH>() {
    private var typePortions = emptyList<PortionType>()
    var onTypePortionClick: ((PortionType) -> Unit)? = null

    class VH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelCategories(portionType: PortionType) {
            binding.tvCategory.text = portionType.shortName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = typePortions.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val portionType = typePortions[position]
        holder.feelCategories(portionType)
        holder.binding.root.setOnClickListener {
            onTypePortionClick?.invoke(portionType)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<PortionType>) {
        typePortions = newItems
        notifyDataSetChanged()
    }

}