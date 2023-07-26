package com.example.presentation.screens.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMenuBinding
import com.example.presentation.screens.home.HomeViewModel
import com.example.presentation.screens.home.menu.adapters.MenuAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {
    override val viewModel by viewModel<MenuViewModel>()
    private val homeViewModel by activityViewModel<HomeViewModel>()

    private lateinit var categoryAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = MenuAdapter()
        viewModel.getMenu()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvMenu) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }
        lifecycleScope.launch {
            viewModel.menuFlow.collect { categoryAdapter.updateItems(it) }
        }
        binding.btCreate.setOnClickListener {
            homeViewModel.toCreateScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMenu()
    }
}