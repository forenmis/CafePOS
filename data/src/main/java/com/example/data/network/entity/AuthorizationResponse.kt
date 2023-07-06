package com.example.data.network.entity

import com.google.gson.annotations.SerializedName

data class AuthorizationResponse(
    @SerializedName("sessionToken")
    val token: String
)