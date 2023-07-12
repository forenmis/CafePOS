package com.example.presentation.screens.home.create.category_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.CategoryBottomSheetBinding
import com.example.presentation.screens.home.create.CreateMenuItemFragment
import com.example.presentation.screens.home.create.CreateMenuItemFragment.Companion.REQUEST_CATEGORY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCategoryBottomSheet : BottomSheetDialogFragment() {
    private var _binding: CategoryBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<BottomSheetViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter()
        viewModel.loadCategories()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CategoryBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
            categoryAdapter.onCategoryClick = { category ->
                val bundle = Bundle()
                bundle.putLong(CreateMenuItemFragment.BUNDLE_KEY_CATEGORY, category.id)
                parentFragmentManager.setFragmentResult(REQUEST_CATEGORY, bundle)
                dismiss()
            }
        }
        lifecycleScope.launch { viewModel.categoryFlow.collect { categoryAdapter.updateItems(it) } }
    }


}