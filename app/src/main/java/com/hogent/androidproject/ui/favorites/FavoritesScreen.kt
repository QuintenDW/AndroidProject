package com.hogent.androidproject.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.hogent.androidproject.ui.components.GameList

@Composable
fun FavoritesScreen( favoriteViewModel: FavoriteViewModel, modifier: Modifier = Modifier,) {
    val favoriteUIState by favoriteViewModel.favoriteUIState.collectAsState()
    GameList(gameList = favoriteUIState.favoriteGamesList, favoriteViewModel = favoriteViewModel)
}