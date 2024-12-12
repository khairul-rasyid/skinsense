package com.rasyid.skinsense.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyid.skinsense.data.SkinSenseRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val skinSenseRepository: SkinSenseRepository) : ViewModel() {

    fun getHistories() = skinSenseRepository.getHistories()

    fun deleteHistory(id: Int) {
        viewModelScope.launch {
            skinSenseRepository.deleteHistory(id)
        }
    }
}