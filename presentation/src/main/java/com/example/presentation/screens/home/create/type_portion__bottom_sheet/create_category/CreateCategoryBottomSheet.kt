package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.BottomSheetCreateCategoryBinding
import com.example.presentation.screens.home.create.category_bottom_sheet.SelectCategoryBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateCategoryBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetCreateCategoryBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<CreateCategoryViewModel>()
    private val imagesAdapter by lazy { CreateCategoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetCreateCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.imagesForCategoryFlow.collect { imagesAdapter.updateItems(it) }
        }
        lifecycleScope.launch {
            viewModel.completeFillFlow.collect { binding.btAdd.isEnabled = it }
        }
        with(binding.rvIcons) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = imagesAdapter
            imagesAdapter.onImageClick = { viewModel.selectImageCategory(it) }
        }
        with(binding) {
            etName.doAfterTextChanged { viewModel.editName(it.toString()) }
            btAdd.setOnClickListener {
                viewModel.saveMenuCategory()
                val categoryBottomSheet = SelectCategoryBottomSheet()
                categoryBottomSheet.show(parentFragmentManager, "choose_category")
                dismiss()
            }
        }
    }
}