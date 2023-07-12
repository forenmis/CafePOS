package com.example.presentation.screens.home.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.os.BundleCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.domain.entity.MenuCategoryIcon
import com.example.domain.entity.PortionType
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCreateMenuItemBinding
import com.example.presentation.screens.home.create.category_bottom_sheet.SelectCategoryBottomSheet
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.SelectTypePortionBottomSheet
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateMenuItemFragment : BaseFragment<FragmentCreateMenuItemBinding>() {
    private val viewModel by viewModel<CreateMenuViewModel>()
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCreateMenuItemBinding {
        return FragmentCreateMenuItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(REQUEST_CATEGORY) { _, bundle ->
            val id = bundle.getLong(BUNDLE_KEY_CATEGORY)
            viewModel.loadCategoryById(id)
        }
        setFragmentResultListener(REQUEST_TYPE) { _, bundle ->
            val portionType =
                BundleCompat.getParcelable(bundle, BUNDLE_KEY_TYPE, PortionType::class.java)
                    ?: error("empty parcelable")
            binding.btTypePortion.text = portionType.shortName

        }
        binding.btCategory.setOnClickListener {
            val categoryBottomSheet = SelectCategoryBottomSheet()
            categoryBottomSheet.show(parentFragmentManager, "choose_category")
        }
        binding.btTypePortion.setOnClickListener {
            val portionTypeBottomSheet = SelectTypePortionBottomSheet()
            portionTypeBottomSheet.show(parentFragmentManager, "show_type_portions")
        }
        lifecycleScope.launch {
            viewModel.chooseCategoryFlow.collect { category ->
                with(binding.btCategory) {
                    text = category.name
                    val categoryIcon = getIconByCategory(category.icon)
                    setIconResource(categoryIcon)
                }
            }
        }

    }

    @DrawableRes
    private fun getIconByCategory(menuCategoryIcon: MenuCategoryIcon): Int {
        return when (menuCategoryIcon) {
            MenuCategoryIcon.Coffee -> R.drawable.ic_coffee
            MenuCategoryIcon.Cake -> R.drawable.ic_cake
            MenuCategoryIcon.Other -> R.drawable.ic_something
        }
    }

    companion object {
        const val REQUEST_CATEGORY = "requestKeyCategory"
        const val BUNDLE_KEY_CATEGORY = "bundleKeyCategory"
        const val REQUEST_TYPE = "requestKeyType"
        const val BUNDLE_KEY_TYPE = "bundleKeyType"
    }
}

