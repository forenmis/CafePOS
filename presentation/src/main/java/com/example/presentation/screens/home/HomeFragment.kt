package com.example.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.screens.home.create.Pages
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by activityViewModel<HomeViewModel>()

    private val callback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomBar.selectedItemId = Pages.findPageByPosition(position).menuId
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeAdapter = HomeAdapter(
            listOf(Pages.Orders, Pages.Menu, Pages.Settings),
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )
        binding.vpContainerHome.adapter = homeAdapter
        binding.vpContainerHome.isUserInputEnabled = false
        binding.bottomBar.setOnItemSelectedListener { item ->
            binding.vpContainerHome.currentItem = Pages.findPageByMenuId(item.itemId).position
            return@setOnItemSelectedListener true
        }
        lifecycleScope.launch {
            viewModel.menuToCreateScreenFlow.collect {
                val action = HomeFragmentDirections.actionHomeFragmentToCreateMenuItemFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.vpContainerHome.registerOnPageChangeCallback(callback)
    }

    override fun onStop() {
        super.onStop()
        binding.vpContainerHome.unregisterOnPageChangeCallback(callback)
    }
}