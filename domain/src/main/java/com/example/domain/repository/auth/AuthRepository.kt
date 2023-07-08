package com.example.domain.repository.auth

interface AuthRepository {

    suspend fun registration(username: String, email: String, password: String)

    suspend fun login(username: String, password: String)

    suspend fun logOut()
}