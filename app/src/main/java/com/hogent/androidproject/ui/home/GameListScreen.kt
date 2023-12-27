package com.hogent.androidproject.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.components.GameList

@Composable
fun GameListScreen(apiState: GameApiState,
                   gameList: List<Game>,
                   onButtonClicked: () -> Unit,
                   addToFavorites: (Game) -> Unit,
                   isFavorite: (Game) -> Boolean) {

    when(apiState) {
        is GameApiState.Success -> GameList(gameList = gameList,
            onButtonClicked = onButtonClicked, addToFavorites = addToFavorites,
            isFavorite = isFavorite)
        is GameApiState.Loading -> Text("Laden...")
        is GameApiState.Error -> Text("Kan lijst niet laden")
    }

}