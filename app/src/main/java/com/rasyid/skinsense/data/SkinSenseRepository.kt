package com.rasyid.skinsense.data

import android.service.autofill.FillEventHistory
import com.rasyid.skinsense.data.local.room.HistoryDao
import com.rasyid.skinsense.data.remote.retrofit.ApiService

class SkinSenseRepository(
    private val apiService: ApiService,
    private val historyDao: HistoryDao
) {

    companion object {
        private const val TAG = "SkinSenseRepository"

        @Volatile
        private var instance: SkinSenseRepository? = null
        fun getInstance(
            apiService: ApiService,
            historyDao: HistoryDao
        ): SkinSenseRepository =
            instance ?: synchronized(this) {
                instance ?: SkinSenseRepository(apiService, historyDao)
            }.also { instance = it }
    }
}