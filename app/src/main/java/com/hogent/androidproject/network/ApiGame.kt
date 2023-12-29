package com.hogent.androidproject.network

import com.hogent.androidproject.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGame(val id: Int,
                   val title: String,
                   val thumbnail: String,
                   @SerialName(value = "game_url")val gameURL: String,
                   val developer: String,
                   @SerialName(value = "release_date")val releaseDate: String,
                   @SerialName(value = "short_description") val description: String,
                   @SerialName(value = "freetogame_profile_url") val profileURL: String,
                   val genre: String,
                   val platform: String,
                   val publisher: String)

fun List<ApiGame>.asDomainObjects(): List<Game> {
    val domainList = this.map {
        Game(it.id,it.title,it.description,it.genre,it.platform,it.publisher,false)
    }
    return domainList
}

fun Flow<List<ApiGame>>.asDomainObjects(): Flow<List<Game>> {
    return this.map {
        it.asDomainObjects()
    }
}