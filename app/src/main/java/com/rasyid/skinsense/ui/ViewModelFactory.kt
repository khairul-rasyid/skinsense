package com.rasyid.skinsense.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rasyid.skinsense.data.SkinSenseRepository
import com.rasyid.skinsense.di.Injection
import com.rasyid.skinsense.ui.history.HistoryViewModel
import com.rasyid.skinsense.ui.settings.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val skinSenseRepository: SkinSenseRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(skinSenseRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(skinSenseRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }



    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}