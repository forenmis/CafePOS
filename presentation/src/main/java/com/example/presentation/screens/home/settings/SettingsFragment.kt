package com.example.presentation.screens.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentSettingsBinding
import com.example.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    override val viewModel by viewModel<SettingsViewModel>()
    private val homeViewModel by activityViewModel<HomeViewModel>()
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivExit.setOnClickListener { viewModel.exit() }
        lifecycleScope.launch { viewModel.screenFLow.collect { homeViewModel.toLoginScreen() } }
    }
}