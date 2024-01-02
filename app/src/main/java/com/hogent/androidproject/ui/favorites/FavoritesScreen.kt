package com.hogent.androidproject.ui.favorites

import androidx.compose.runtime.Composable
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.components.GameList

/**
 * Shows list of user's favorites
 */
@Composable
fun FavoritesScreen(favorites: List<Game>, goBack: () -> Unit,
                    addToFavorites: (Game) -> Unit) {
    GameList(gameList = favorites, onButtonClicked = goBack, addToFavorites = addToFavorites)
}