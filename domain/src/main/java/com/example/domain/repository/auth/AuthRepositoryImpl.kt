package com.example.domain.repository.auth

import com.example.data.network.NetworkManager

internal class AuthRepositoryImpl(private val networkManager: NetworkManager) : AuthRepository {
    override suspend fun registration(username: String, email: String, password: String): String {
        val token = networkManager.registration(username, email, password)
        return token
    }

    override suspend fun login(username: String, password: String): String {
        val token = networkManager.login(username, password)
        return token
    }

}