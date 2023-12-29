package com.hogent.androidproject.model

data class Game(val id: Int,val title: String,
                val description: String,
                val genre: String,
                val platform: String,
                val publisher: String,
                val isFavorite: Boolean,
    )

/**
 * Used to update the isFavorite value in db.
 * If the target entity (dbGame) is specified via entity then the parameters can be of arbitrary
 * POJO types that will be interpreted as partial entities
 */
data class Favorite(val id: Int,val isFavorite: Boolean)