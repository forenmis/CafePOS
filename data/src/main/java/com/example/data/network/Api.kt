package com.example.data.network

import com.example.data.network.entity.AuthorizationRequest
import com.example.data.network.entity.AuthorizationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("login")
    @Headers(
        "X-Parse-Application-Id: Bylu0hrGg1dRnUKcLcuoKtS585h8JPLCQev45CHd",
        "X-Parse-REST-API-Key: 3tiBX9V0WFBaTn0BDGpaZXGNNmQUpw1Njl3Bdkvp",
        "X-Parse-Revocable-Session: 1"
    )
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): AuthorizationResponse


    @POST("users")
    @Headers(
        "X-Parse-Application-Id: Bylu0hrGg1dRnUKcLcuoKtS585h8JPLCQev45CHd",
        "X-Parse-REST-API-Key: 3tiBX9V0WFBaTn0BDGpaZXGNNmQUpw1Njl3Bdkvp",
        "X-Parse-Revocable-Session: 1",
        "Content-Type: application/json"
    )
    suspend fun registration(@Body request: AuthorizationRequest): AuthorizationResponse

}




