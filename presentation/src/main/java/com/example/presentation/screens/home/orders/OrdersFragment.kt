package com.example.presentation.screens.home.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentOrdersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : BaseFragment<FragmentOrdersBinding, OrdersViewModel>() {
    override val viewModel by viewModel<OrdersViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrdersBinding {
        return FragmentOrdersBinding.inflate(inflater, container, false)
    }
}