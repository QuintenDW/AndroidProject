package com.hogent.androidproject.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hogent.androidproject.data.DataSource
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.GameApi
import com.hogent.androidproject.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState = _gameUiState.asStateFlow()

    var gameApiState: GameApiState by mutableStateOf(GameApiState.Loading)
        private set


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
        getGames(_gameUiState.value.platform,_gameUiState.value.category)
    }

    /**
     * Creates gamelist based on platform and category
     */
    private fun getGames(platform: String,category: String) {
        viewModelScope.launch {
            try {
                val gamesList = GameApi.retrofitService.getGames(platform.lowercase(),category.lowercase())
                _gameUiState.update {
                    it.copy(gameList = gamesList.asDomainObjects())
                }
                gameApiState = GameApiState.Success(gamesList.asDomainObjects())
            } catch(e: IOException) {
                gameApiState = GameApiState.Error
            }

        }
    }
    private fun filterGames(platform: String,category: String): List<Game> {
        return DataSource().loadGames().filter {
            it.platform.uppercase().contains(platform.uppercase()) && it.genre.uppercase().equals(category.uppercase())
        }
    }
}