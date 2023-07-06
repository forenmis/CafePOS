package com.example.data.network

interface NetworkManager {

    suspend fun registration(username: String, email: String, password: String): String

    suspend fun login(username: String, password: String): String

}