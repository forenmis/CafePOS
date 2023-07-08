package com.example.domain.repository.auth

import com.example.data.network.NetworkManager
import com.example.data.preferences.AppPreferences

internal class AuthRepositoryImpl(
    private val networkManager: NetworkManager,
    private val appPreferences: AppPreferences
) : AuthRepository {

    override suspend fun registration(username: String, email: String, password: String) {
        val token = networkManager.registration(username, email, password)
        appPreferences.saveToken(token)
    }

    override suspend fun login(username: String, password: String) {
        val token = networkManager.login(username, password)
        appPreferences.saveToken(token)
    }

    override suspend fun logOut() {
        appPreferences.clear()
    }

}