package com.example.presentation.screens.home.create.type_portion__bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.TypePortionBottomSheetBinding
import com.example.presentation.screens.home.create.CreateMenuItemFragment.Companion.BUNDLE_KEY_TYPE
import com.example.presentation.screens.home.create.CreateMenuItemFragment.Companion.REQUEST_TYPE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectTypePortionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: TypePortionBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<SelectTypeViewModel>()
    private lateinit var portionAdapter: PortionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portionAdapter = PortionAdapter()
        viewModel.loadPortionTypes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TypePortionBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context)
            adapter = portionAdapter
            portionAdapter.onTypePortionClick = {
                val bundle = Bundle()
                bundle.putParcelable(BUNDLE_KEY_TYPE, it)
                parentFragmentManager.setFragmentResult(REQUEST_TYPE, bundle)
                dismiss()
            }
        }
        lifecycleScope.launch { viewModel.portionTypesFlow.collect { portionAdapter.updateItems(it) } }
    }
}