package com.example.presentation

import com.example.presentation.screens.home.HomeViewModel
import com.example.presentation.screens.home.menu.MenuViewModel
import com.example.presentation.screens.login.LoginViewModel
import com.example.presentation.screens.main.MainViewModel
import com.example.presentation.screens.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { RegistrationViewModel(get()) }
    viewModel { MenuViewModel(get()) }
}