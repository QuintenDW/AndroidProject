package com.hogent.androidproject.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.R
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.theme.AndroidprojectTheme

/**
 * Show 1 game in the list
 */
@Composable
fun GameItem(
    game: Game,
    modifier: Modifier = Modifier,
    isAlreadyFavorite: Boolean = false,
    onFavorite: (Game) -> Unit = {},

) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(modifier = modifier.animateContentSize( animationSpec = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMediumLow))
    , colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))) {
            FavoriteButton(isFavorite = isAlreadyFavorite) {
                onFavorite(game)
            }
            GameInfo(title = game.title, genre = game.genre, platforms = game.platform)
            Spacer(modifier = Modifier.weight(1f))
            GameItemButton(expanded = expanded, onClick= { expanded = !expanded })
        }
        if (expanded) {
            ExtraGameInfo(description = game.description, publisher = game.publisher)
        }

    }

}

@Composable
private fun ExtraGameInfo(description: String,publisher: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.over), modifier = Modifier.padding(dimensionResource(
            R.dimen.padding_medium)), fontWeight = FontWeight.Bold
            )
            Text(text = description, modifier = Modifier.padding(dimensionResource(
                R.dimen.padding_medium)))
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.uitgevergame),modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_medium)), fontWeight = FontWeight.Bold)
            Text(text = publisher, modifier = Modifier.padding(dimensionResource(
                R.dimen.padding_medium)))
        }

    }
}

/**
 * To see extra information from a game
 */
@Composable
private fun GameItemButton(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = "Toont extra info")
    }
}

@Composable
private fun FavoriteButton(isFavorite: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick,modifier = modifier.testTag(stringResource(
        R.string.favoriete_knop
    ))) {
        Icon(imageVector = if(isFavorite)  Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(
            R.string.favoriete_knop
        ))
    }
}

/**
 * Info about the game
 */
@Composable
private fun GameInfo(title: String,  genre: String, platforms: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = title, modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = platforms, modifier = Modifier.padding(10.dp))
            Text(text = genre, modifier = Modifier.padding(10.dp))
        }
    }

}
@Preview
@Composable
private fun GameItemPreview() {
    AndroidprojectTheme {
        GameItem(
            Game(1,"Overwatch 2",
                "A hero-focused first-person team shooter from Blizzard Entertainment.",
                "Shooter",
                "PC (Windows)",
                "Activision Blizzard",
                false
            )
        )
    }

}