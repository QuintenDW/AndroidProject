package com.hogent.androidproject.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hogent.androidproject.network.GameApi
import com.hogent.androidproject.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
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
                val gamesList = gameRepository.getGames(platform.lowercase(),category.lowercase())
                _gameUiState.update {
                    it.copy(gameList = gamesList)
                }
                gameApiState = GameApiState.Success(gamesList)
            } catch(e: IOException) {
                gameApiState = GameApiState.Error
            }

        }
    }

    /**
     * Factory to use repository, tells how to handle the parameter of viewmodel
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }
}