package com.example.cleanarchitecture.app

import android.app.Application
import com.example.data.dataModule
import com.example.domain.domainModule
import com.example.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(presentationModule, domainModule, dataModule)
        }
    }
}