package com.rasyid.skinsense.di

import android.content.Context
import com.rasyid.skinsense.data.SkinSenseRepository
import com.rasyid.skinsense.data.datastore.SettingsPreference
import com.rasyid.skinsense.data.datastore.dataStore
import com.rasyid.skinsense.data.local.room.HistoryDatabase
import com.rasyid.skinsense.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): SkinSenseRepository {
        val apiService = ApiConfig.getApiService()
        val database = HistoryDatabase.getInstance(context)
        val dao = database.historyDao()
        val preference = SettingsPreference.getInstance(context.dataStore)
        return SkinSenseRepository.getInstance(apiService, dao, preference)
    }
}