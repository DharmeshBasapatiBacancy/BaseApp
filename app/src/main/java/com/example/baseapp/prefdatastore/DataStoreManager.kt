package com.example.baseapp.prefdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val MERCHANT_DATASTORE ="merchant_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)

class DataStoreManager (val context: Context) {

    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
        val USERID = stringPreferencesKey("USERID")
    }

    suspend fun saveToDataStore(email: String, userId: String) {
        context.dataStore.edit {
            it[EMAIL] = email
            it[USERID] = userId
        }
    }

    // Create a name flow to retrieve name from the preferences
    val userEmailFlow: Flow<String> = context.dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val userIdFlow: Flow<String> = context.dataStore.data.map {
        it[USERID] ?: ""
    }

    /*suspend fun savetoDataStore(phonebook: PhoneBook) {
        context.dataStore.edit {

            it[NAME] = phonebook.name
            it[PHONE_NUMBER] = phonebook.phoneNumber
            it[ADDRESS] = phonebook.address

        }
    }*/

    /*suspend fun getFromDataStore() = context.dataStore.data.map {
        Phonebook(
            name = it[NAME]?:"",
            phoneNumber = it[PHONE_NUMBER]?:"",
            address = it[ADDRESS]?:""
        )
    }*/
}
