package com.rasyid.skinsense.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsPreference private constructor(private val dataStore: DataStore<Preferences>){
    private val sTHEME = booleanPreferencesKey("dark_mode")

    fun getTheme() : Flow<Boolean> {
        return dataStore.data.map { preference ->
            preference[sTHEME] ?: false
        }
    }

    suspend fun setDarkMode(isDarkMode : Boolean) {
        dataStore.edit { preference ->
            preference[sTHEME] = isDarkMode
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : SettingsPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>) : SettingsPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}