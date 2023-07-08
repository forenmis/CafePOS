package com.example.presentation.screens.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }
}