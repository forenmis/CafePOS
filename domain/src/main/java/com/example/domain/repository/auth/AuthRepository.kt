package com.example.domain.repository.auth

interface AuthRepository {

    suspend fun registration(username: String, email: String, password: String): String

    suspend fun login(username: String, password: String): String
}