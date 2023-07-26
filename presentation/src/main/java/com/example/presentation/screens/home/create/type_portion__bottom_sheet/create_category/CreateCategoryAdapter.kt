package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemCreateCategoryBinding

class CreateCategoryAdapter : RecyclerView.Adapter<CreateCategoryAdapter.VH>() {

    private var images = emptyList<ImageCategory>()
    var onImageClick: ((ImageCategory) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCreateCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val image = images[position]
        holder.feelList(image)
        holder.binding.ivIcon.setOnClickListener {
            onImageClick?.invoke(image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ImageCategory>) {
        images = newItems
        notifyDataSetChanged()
    }

    class VH(val binding: ItemCreateCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelList(image: ImageCategory) {
            with(binding.ivIcon) {
                setImageResource(image.res)
                setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        if (image.isChanged) R.color.white else android.R.color.transparent
                    )
                )
            }
        }
    }
}