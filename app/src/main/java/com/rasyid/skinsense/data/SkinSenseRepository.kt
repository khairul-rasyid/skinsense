package com.rasyid.skinsense.data

import com.rasyid.skinsense.data.datastore.SettingsPreference
import com.rasyid.skinsense.data.local.room.HistoryDao
import com.rasyid.skinsense.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class SkinSenseRepository(
    private val apiService: ApiService,
    private val historyDao: HistoryDao,
    private val preference: SettingsPreference
) {

    fun getDarkMode() : Flow<Boolean> {
        return preference.getDarkMode()
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        preference.setDarkMode(isDarkMode)
    }

    companion object {
        private const val TAG = "SkinSenseRepository"

        @Volatile
        private var instance: SkinSenseRepository? = null
        fun getInstance(
            apiService: ApiService,
            historyDao: HistoryDao,
            preference: SettingsPreference
        ): SkinSenseRepository =
            instance ?: synchronized(this) {
                instance ?: SkinSenseRepository(apiService, historyDao, preference)
            }.also { instance = it }
    }
}