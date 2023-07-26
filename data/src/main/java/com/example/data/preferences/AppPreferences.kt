package com.example.data.preferences

interface AppPreferences {

    fun saveToken(token: String)

    fun getToken(): String?
    fun checkToken(): Boolean

    fun clear()


}