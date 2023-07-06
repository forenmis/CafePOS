package com.example.domain

import com.example.domain.repository.auth.AuthRepository
import com.example.domain.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
}