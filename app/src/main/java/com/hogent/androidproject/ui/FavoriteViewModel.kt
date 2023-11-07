package com.hogent.androidproject.ui

import androidx.lifecycle.ViewModel
import com.hogent.androidproject.data.FavoriteUIState
import com.hogent.androidproject.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoriteViewModel : ViewModel() {
    private val _favoriteUIState = MutableStateFlow(FavoriteUIState())
    val favoriteUIState: StateFlow<FavoriteUIState> = _favoriteUIState.asStateFlow()

    fun favoriteGame(game: Game) {
        _favoriteUIState.update {
            if (hasGame(game)) {
                it.copy(favoriteGamesList = it.favoriteGamesList - game)
            } else {
                it.copy(favoriteGamesList = it.favoriteGamesList + game)
            }
        }
    }
    fun hasGame(game: Game): Boolean {
        return _favoriteUIState.value.favoriteGamesList.contains(game)
    }
}