package com.hogent.androidproject.ui.main

import androidx.lifecycle.ViewModel
import com.hogent.androidproject.data.DataSource
import com.hogent.androidproject.model.Game
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
    fun createGameList() {
        _gameUiState.update {
            it.copy(
                gameList = filterGames(it.platform,it.category)
            )
        }
    }
    private fun filterGames(platform: String,category: String): List<Game> {
        return DataSource().loadGames().filter {
            it.platforms.uppercase().contains(platform.uppercase()) && it.genre.uppercase().equals(category.uppercase())
        }

    }
}