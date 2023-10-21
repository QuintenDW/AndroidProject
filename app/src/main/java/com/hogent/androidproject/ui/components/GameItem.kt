package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.R
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.theme.AndroidprojectTheme

@Composable
fun GameItem(game: Game, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(dimensionResource(R.dimen.padding_medium))) {
        GameInfo(title = game.title, genre = game.genre, platforms = game.platforms)
    }
}

@Composable
fun GameInfo(title: String,  genre: String, platforms: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, modifier = Modifier.padding(10.dp))
        Text(text = platforms, modifier = Modifier.padding(10.dp))
        Text(text = genre, modifier = Modifier.padding(10.dp))
    }
}
@Preview
@Composable
private fun GameItemPreview() {
    AndroidprojectTheme {
        GameItem( Game("Overwatch 2",
            "A hero-focused first-person team shooter from Blizzard Entertainment.",
            "Shooter",
            "PC (Windows)",
            "Activision Blizzard"
        ))
    }

}