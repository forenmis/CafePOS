package com.example.presentation

import com.example.presentation.screens.home.HomeViewModel
import com.example.presentation.screens.home.create.CreateMenuViewModel
import com.example.presentation.screens.home.create.category_bottom_sheet.BottomSheetViewModel
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.SelectTypeViewModel
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_category.CreateCategoryViewModel
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.create_type_portion.CreateTypePortionViewModel
import com.example.presentation.screens.home.menu.MenuViewModel
import com.example.presentation.screens.home.orders.OrdersViewModel
import com.example.presentation.screens.home.settings.SettingsViewModel
import com.example.presentation.screens.login.LoginViewModel
import com.example.presentation.screens.login_check_screen.LoginCheckViewModel
import com.example.presentation.screens.main.MainViewModel
import com.example.presentation.screens.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { RegistrationViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { BottomSheetViewModel(get()) }
    viewModel { CreateMenuViewModel(get(), get()) }
    viewModel { SelectTypeViewModel(get()) }
    viewModel { CreateTypePortionViewModel(get()) }
    viewModel { CreateCategoryViewModel(get()) }
    viewModel { LoginCheckViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { OrdersViewModel() }
}