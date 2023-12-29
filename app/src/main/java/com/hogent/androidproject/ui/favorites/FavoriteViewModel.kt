package com.hogent.androidproject.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hogent.androidproject.GameApplication
import com.hogent.androidproject.data.GameRepository
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(private val gameRepository: GameRepository) : ViewModel() {

    lateinit var favoriteUIState : StateFlow<FavoriteUIState>


    init {
        getFavoriteGames()
    }

    /**
     * Creates gamelist based on platform and category
     */
    private fun getFavoriteGames() {
            favoriteUIState = gameRepository.getFavorites().map { FavoriteUIState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = FavoriteUIState()
                )
    }
    fun favoriteGame(game: Game) {
        viewModelScope.launch { saveFavorite(Favorite(game.id,!game.isFavorite)) }
    }

    private suspend fun saveFavorite(favorite: Favorite) {
        gameRepository.updateFavorite(favorite)
    }

    /**
     * Factory to use repository, tells how to handle the parameter of viewmodel
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                FavoriteViewModel(gameRepository = gameRepository)
            }
        }
    }
}