package com.hogent.androidproject.ui

import androidx.lifecycle.ViewModel
import com.hogent.androidproject.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState = _gameUiState.asStateFlow()

    fun setPlatform(platform: String) {
        _gameUiState.update {
            it.copy(platform = platform)
        }
    }
    fun setCategory(category: String) {
        _gameUiState.update {
            it.copy(category = category)
        }
    }
}