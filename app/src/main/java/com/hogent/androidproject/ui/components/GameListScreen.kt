package com.hogent.androidproject.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import com.hogent.androidproject.ui.home.GameViewModel

@Composable
fun GameListScreen(gameViewModel: GameViewModel,favoriteViewModel: FavoriteViewModel, modifier: Modifier = Modifier,) {
    val gameUiState by gameViewModel.gameUiState.collectAsState()
    GameList(gameList = gameUiState.gameList, favoriteViewModel = favoriteViewModel)
}