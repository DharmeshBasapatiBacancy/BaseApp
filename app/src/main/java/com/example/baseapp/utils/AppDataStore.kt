package com.example.baseapp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {

    companion object {

        private const val DATASTORE_NAME = "appDataStore"

        private val EMAIL_KEY = stringPreferencesKey("email")

        private val Context.tangoDataStore by preferencesDataStore(name = DATASTORE_NAME)

    }

    suspend fun setUserEmail(email: String) {
        context.tangoDataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    val userEmail: Flow<String>
        get() = context.tangoDataStore.data.map {
            it[EMAIL_KEY] ?: ""
        }

}