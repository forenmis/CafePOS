package com.example.data.network

import com.example.data.network.entity.AuthorizationRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class NetworkManagerImpl : NetworkManager {
    private val api: Api
    private val gson: Gson

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        gson = GsonBuilder().serializeNulls().create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://parseapi.back4app.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create(Api::class.java)
    }

    override suspend fun registration(username: String, email: String, password: String): String {
        val request = AuthorizationRequest(password, username, email)
        val response = api.registration(request)
        return response.token
    }

    override suspend fun login(username: String, password: String): String {
        val response = api.login(username, password)
        return response.token
    }
}