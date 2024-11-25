package com.rasyid.skinsense.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyid.skinsense.data.datastore.SettingsPreference
import kotlinx.coroutines.launch

class SettingsViewModel(private val preference: SettingsPreference) : ViewModel() {

    fun getDarkMode() : LiveData<Boolean> {
        return preference.getTheme().asLiveData()
    }

    fun setDarkMode(isDarkMode : Boolean) {
        viewModelScope.launch {
            preference.setDarkMode(isDarkMode)
        }
    }
}