package com.rasyid.skinsense.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.rasyid.skinsense.data.datastore.SettingsPreference
import com.rasyid.skinsense.data.local.entity.HistoryEntity
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

    fun getHistories() : LiveData<Result<List<HistoryEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val result : LiveData<Result<List<HistoryEntity>>> =
                historyDao.getHistories().map {
                    Result.Success(it)
                }
            emitSource(result)
        } catch (e: Exception) {
            Log.e(TAG, "getHistories: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun insertHistory(product: String, feature: String, material: String) {
        val newHistory = HistoryEntity(
            product = product,
            feature = feature,
            material = material
        )
        historyDao.insertHistory(newHistory)
    }
    
    suspend fun deleteHistory(id: Int) {
        historyDao.deleteHistory(id)
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