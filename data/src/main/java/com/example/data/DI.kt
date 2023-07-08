package com.example.data

import com.example.data.network.NetworkManager
import com.example.data.network.NetworkManagerImpl
import com.example.data.preferences.AppPreferences
import com.example.data.preferences.AppPreferencesImpl
import org.koin.dsl.module

val dataModule = module {
    single<NetworkManager> { NetworkManagerImpl() }
    single<AppPreferences>{AppPreferencesImpl(get())}
}