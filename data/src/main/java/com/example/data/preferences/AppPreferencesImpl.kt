package com.example.data.preferences

import android.content.Context
import androidx.core.content.edit

class AppPreferencesImpl(context: Context) : AppPreferences {

    private val appPreference =
        context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)


    override fun saveToken(token: String) {
        appPreference.edit { putString(TOKEN_KEY, token) }
    }

    override fun getToken(): String? {
        return appPreference.getString(TOKEN_KEY, null)
    }

    override fun clear() {
        appPreference.edit {
            remove(TOKEN_KEY)
        }
    }

    companion object {
        private const val APP_PREFERENCES_NAME = "app.preferences"
        private const val TOKEN_KEY = "token_key"
    }
}