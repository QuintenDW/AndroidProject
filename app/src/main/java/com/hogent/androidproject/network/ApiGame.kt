package com.hogent.androidproject.network

import com.hogent.androidproject.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGame(val title: String,
                @SerialName(value = "short_description") val description: String,
                val genre: String,
                val platform: String,
                val publisher: String)

fun List<ApiGame>.asDomainObjects(): List<Game> {
    var domainList = this.map {
        Game(it.title,it.description,it.genre,it.platform,it.publisher)
    }
    return domainList
}