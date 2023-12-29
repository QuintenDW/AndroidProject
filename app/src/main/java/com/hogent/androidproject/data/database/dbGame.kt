package com.hogent.androidproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game

@Entity(tableName="games")
data class dbGame(
    @PrimaryKey
    val id: Int,
    val title: String = "",
    val description: String = "",
    val genre: String = "",
    val platform: String = "",
    val publisher: String = "",
    val isFavorite: Boolean = false
)

/**
 * Used to update the isFavorite value in db.
 * If the target entity (dbGame) is specified via entity then the parameters can be of arbitrary
 * POJO types that will be interpreted as partial entities
 */
data class dbFavorite(val id: Int,val isFavorite: Boolean)
fun dbGame.asDomainGame(): Game {
    return Game(this.id,this.title,this.description,this.genre,this.platform,this.publisher,this.isFavorite)
}

fun Game.asDbGame(): dbGame {
    return dbGame(id= this.id,title = this.title, description = this.description, genre = this.genre,
        platform = this.platform, publisher = this.publisher, isFavorite = this.isFavorite)
}

fun Favorite.asDbFavorite(): dbFavorite {
    return dbFavorite(id=this.id,isFavorite=this.isFavorite)
}


fun List<dbGame>.asDomainGames(): List<Game>{
    return this.map {
        it.asDomainGame() }
}