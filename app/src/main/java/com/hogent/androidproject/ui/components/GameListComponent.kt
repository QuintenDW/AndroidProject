package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hogent.androidproject.R
import com.hogent.androidproject.model.Game


/**
 * List of all free-to-play games
 */
@Composable
fun GameList(
    gameList: List<Game>,
    onButtonClicked: () -> Unit,
    addToFavorites: (Game) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Box(modifier = Modifier
            .weight(1f)
            .testTag(stringResource(id = R.string.game_lijst))) {
            LazyColumn(modifier = modifier) {
                items(gameList) { game ->
                    GameItem(game = game, modifier = Modifier.padding(
                        vertical=dimensionResource(id = R.dimen.padding_vertical),
                        horizontal = dimensionResource(id = R.dimen.padding_medium))
                        .heightIn(min = 110.dp),
                        isAlreadyFavorite =
                    game.isFavorite) {
                        addToFavorites(it)
                    }
                }
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),horizontalArrangement = Arrangement.End) {
            Button(modifier = Modifier.widthIn(min= 200.dp).testTag(stringResource(R.string.annuleer)),onClick = onButtonClicked) {
                Text(stringResource(R.string.annuleer), fontSize = 16.sp)
            }
        }

    }


}