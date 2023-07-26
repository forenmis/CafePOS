package com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_type_portion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.CreateTypePortionBottomSheetBinding
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.SelectTypePortionBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateTypePortionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: CreateTypePortionBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding is null")

    private val viewModel by viewModel<CreateTypePortionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateTypePortionBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { viewModel.completeFillFlow.collect { binding.btAdd.isEnabled = it } }
        with(binding) {
            etName.doAfterTextChanged { viewModel.saveName(it.toString()) }
            etShortName.doAfterTextChanged { viewModel.saveShortName(it.toString()) }
            btAdd.setOnClickListener {
                viewModel.savePortionType()
                val portionTypeBottomSheet = SelectTypePortionBottomSheet()
                portionTypeBottomSheet.show(parentFragmentManager, "show_type_portions")
                dismiss()
            }
        }
    }

}