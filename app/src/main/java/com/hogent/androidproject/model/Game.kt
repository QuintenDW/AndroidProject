package com.hogent.androidproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(val title: String,val description: String,val genre: String,val platforms: String,val publisher: String)
