package com.example.data.network.entity

import com.google.gson.annotations.SerializedName

data class AuthorizationRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
)
