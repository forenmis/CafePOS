package com.example.presentation.screens.home.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCreateMenuItemBinding

class CreateMenuItemFragment : BaseFragment<FragmentCreateMenuItemBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCreateMenuItemBinding {
        return FragmentCreateMenuItemBinding.inflate(inflater, container, false)
    }
}