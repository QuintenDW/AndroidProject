package com.hogent.androidproject.fake

import com.hogent.androidproject.data.GameRepository
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.asDomainObjects

class FakeApiGameRepository: GameRepository {
    override suspend fun getGames(platform: String, category: String): List<Game> {
        return FakeDataSource.games.asDomainObjects()
    }
}