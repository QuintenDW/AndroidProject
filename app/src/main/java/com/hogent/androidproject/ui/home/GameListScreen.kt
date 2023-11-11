package com.hogent.androidproject.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.hogent.androidproject.ui.components.GameList
import com.hogent.androidproject.ui.favorites.FavoriteViewModel

@Composable
fun GameListScreen(gameViewModel: GameViewModel,favoriteViewModel: FavoriteViewModel, modifier: Modifier = Modifier,) {
    val gameUiState by gameViewModel.gameUiState.collectAsState()
    val gameApiState = gameViewModel.gameApiState
    when(gameApiState) {
        is GameApiState.Success -> GameList(gameList = gameUiState.gameList, favoriteViewModel = favoriteViewModel)
        is GameApiState.Loading -> Text("Laden...")
        is GameApiState.Error -> Text("Kan lijst niet laden")
    }

}