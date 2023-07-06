package com.example.data

import com.example.data.network.NetworkManager
import com.example.data.network.NetworkManagerImpl
import org.koin.dsl.module

val dataModule = module {
    single<NetworkManager> { NetworkManagerImpl() }
}