package com.example.presentation.screens.home.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentOrdersBinding

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrdersBinding {
       return FragmentOrdersBinding.inflate(inflater, container, false)
    }
}