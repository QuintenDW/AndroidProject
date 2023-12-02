package com.hogent.androidproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hogent.androidproject.model.Game

@Entity(tableName="games")
data class dbGame(
    @PrimaryKey
    val id: Int,
    val title: String = "",
    val description: String = "",
    val genre: String = "",
    val platform: String = "",
    val publisher: String = ""
)

fun dbGame.asDomainGame(): Game {
    return Game(this.id,this.title,this.description,this.genre,this.platform,this.publisher)
}

fun Game.asDbGame(): dbGame {
    return dbGame(id= this.id,title = this.title, description = this.description, genre = this.genre, platform = this.platform, publisher = this.publisher)
}

fun List<dbGame>.asDomainGames(): List<Game>{
    return this.map {
        it.asDomainGame() }
}