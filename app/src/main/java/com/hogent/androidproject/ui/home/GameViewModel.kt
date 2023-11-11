package com.hogent.androidproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hogent.androidproject.data.DataSource
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.GameApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

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

    private fun getGames() {
        viewModelScope.launch {
            try {
                val gamesList = GameApi.retrofitService.getGames()
            } catch(e: IOException) {

            }

        }
    }
    private fun filterGames(platform: String,category: String): List<Game> {
        return DataSource().loadGames().filter {
            it.platforms.uppercase().contains(platform.uppercase()) && it.genre.uppercase().equals(category.uppercase())
        }
    }
}