package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.hogent.androidproject.R
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.favorites.FavoriteViewModel

/**
 * List of all free-to-play games
 */
@Composable
fun GameList(
    gameList: List<Game>,
    favoriteViewModel: FavoriteViewModel,
    modifier: Modifier = Modifier,
) {
    val favoriteUIState by favoriteViewModel.favoriteUIState.collectAsState()
    LazyColumn(modifier = modifier) {
        items(gameList) {
            GameItem(game = it, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)), isAlreadyFavorite =
            { favoriteUIState.favoriteGamesList.contains(it) }) {
                favoriteViewModel.favoriteGame(it)
            }
        }
    }
}