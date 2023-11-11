package com.hogent.androidproject.ui.home

import com.hogent.androidproject.model.Game

/**
 * Contains state for the different options you can select
 */
data class GameUiState(
    val platform: String = "PC",
    val category: String = "shooter",
    val gameList: List<Game> = listOf()
)

sealed interface GameApiState {
    data class Success(val gameList: List<Game>) : GameApiState
    object Error : GameApiState
    object Loading : GameApiState
}