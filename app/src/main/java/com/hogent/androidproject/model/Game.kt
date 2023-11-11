package com.hogent.androidproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(val title: String,
                @SerialName(value = "short_description") val description: String,
                val genre: String,
                val platform: String,
                val publisher: String)
