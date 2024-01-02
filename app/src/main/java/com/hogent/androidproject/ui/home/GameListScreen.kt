package com.hogent.androidproject.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.components.GameList

/**
 * Show the list of games retrieved from Free-to-play games api
 */
@Composable
fun GameListScreen(apiState: GameApiState,
                   gameList: List<Game>,
                   onButtonClicked: () -> Unit,
                   addToFavorites: (Game) -> Unit) {

    when(apiState) {
        is GameApiState.Success -> GameList(gameList = gameList,
            onButtonClicked = onButtonClicked, addToFavorites = addToFavorites)
        is GameApiState.Loading -> Text("Laden...")
        is GameApiState.Error -> Text("Kan lijst niet laden")
    }

}