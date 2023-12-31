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
import com.hogent.androidproject.GameApplication
import com.hogent.androidproject.data.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState = _gameUiState.asStateFlow()

    lateinit var gameUIListState : StateFlow<GameListState>

    var gameApiState: GameApiState by mutableStateOf(GameApiState.Loading)
        private set

    /**
     * Sets the platform
     */
    fun setPlatform(platform: String) {
        _gameUiState.update {
            it.copy(platform = platform)
        }
    }

    /**
     * Sets the category
     */
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
        try {
            viewModelScope.launch {
                gameRepository.refresh(platform.lowercase(),category.lowercase()) }
            gameUIListState = gameRepository.getGames(platform.lowercase(),category.lowercase()).map { GameListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = GameListState()
                )
            gameApiState = GameApiState.Success
        }  catch(e: IOException) {
            gameApiState = GameApiState.Error
        }
        }

    /**
     * Function to go to next wizard step
     */
    fun next() {
        when (_gameUiState.value.wizardStep) {
            WizardSteps.PLATFORM -> {
                _gameUiState.update { it.copy(wizardStep = WizardSteps.CATEGORY) }
            }
            WizardSteps.CATEGORY -> {
                _gameUiState.update { it.copy(wizardStep = WizardSteps.LIST) }
            }
            WizardSteps.LIST -> {
                throw IllegalStateException("Geen volgende")
            }
        }
    }

    /**
     * Function to go to back wizard step
     */
    fun back() {
        when (_gameUiState.value.wizardStep) {
            WizardSteps.PLATFORM -> {
                throw IllegalStateException("Geen vroige")
            }
            WizardSteps.CATEGORY -> {
                _gameUiState.update { it.copy(wizardStep = WizardSteps.PLATFORM) }
            }
            WizardSteps.LIST -> {
                _gameUiState.update { it.copy(wizardStep = WizardSteps.CATEGORY) }
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