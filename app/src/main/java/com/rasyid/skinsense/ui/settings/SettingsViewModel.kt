package com.rasyid.skinsense.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyid.skinsense.data.SkinSenseRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val skinSenseRepository: SkinSenseRepository) : ViewModel() {

    fun getDarkMode() : LiveData<Boolean> {
        return skinSenseRepository.getDarkMode().asLiveData()
    }

    fun setDarkMode(isDarkMode : Boolean) {
        viewModelScope.launch {
            skinSenseRepository.setDarkMode(isDarkMode)
        }
    }
}