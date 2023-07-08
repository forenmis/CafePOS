package com.example.domain

import com.example.domain.repository.auth.AuthRepository
import com.example.domain.repository.auth.AuthRepositoryImpl
import com.example.domain.repository.menu.MenuRepository
import com.example.domain.repository.menu.MenuRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<MenuRepository> { MenuRepositoryImpl() }
}