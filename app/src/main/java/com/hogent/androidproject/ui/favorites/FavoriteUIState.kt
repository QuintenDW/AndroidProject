package com.hogent.androidproject.ui.favorites

import com.hogent.androidproject.model.Game

/**
 * Constains state of the user his favorite games.
 */
data class FavoriteUIState(
    val favoriteGamesList: List<Game> = listOf()
)