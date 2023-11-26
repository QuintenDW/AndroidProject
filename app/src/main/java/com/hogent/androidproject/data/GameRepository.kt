package com.hogent.androidproject.data

import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.GameApiService
import com.hogent.androidproject.network.asDomainObjects

interface GameRepository {
    suspend fun getGames(platform: String, category: String): List<Game>
}

class ApiGameRepository(private val gameApiService: GameApiService) : GameRepository {
    override suspend fun getGames(platform: String, category: String): List<Game> {
        return gameApiService.getGames(platform,category).asDomainObjects()
    }
}