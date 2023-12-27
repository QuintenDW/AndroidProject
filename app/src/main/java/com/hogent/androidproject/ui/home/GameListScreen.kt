package com.hogent.androidproject.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.components.GameList

@Composable
fun GameListScreen(gameViewModel: GameViewModel,
                   onButtonClicked: () -> Unit,
                   addToFavorites: (Game) -> Unit,
                   isFavorite: (Game) -> Boolean) {
    val gameUiState by gameViewModel.gameUIListState.collectAsState()

    val gameApiState = gameViewModel.gameApiState
    when(gameApiState) {
        is GameApiState.Success -> GameList(gameList = gameUiState.gameList,
            onButtonClicked = onButtonClicked, addToFavorites = addToFavorites,
            isFavorite = isFavorite)
        is GameApiState.Loading -> Text("Laden...")
        is GameApiState.Error -> Text("Kan lijst niet laden")
    }

}