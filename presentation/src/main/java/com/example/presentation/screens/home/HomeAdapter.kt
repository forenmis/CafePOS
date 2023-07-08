package com.example.presentation.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.presentation.screens.home.create.Pages
import com.example.presentation.screens.home.menu.MenuFragment
import com.example.presentation.screens.home.orders.OrdersFragment
import com.example.presentation.screens.home.settings.SettingsFragment

class HomeAdapter( private val items: List<Pages>,fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position]){
            Pages.Menu -> MenuFragment()
            Pages.Orders -> OrdersFragment()
            Pages.Settings -> SettingsFragment()
        }
    }

}