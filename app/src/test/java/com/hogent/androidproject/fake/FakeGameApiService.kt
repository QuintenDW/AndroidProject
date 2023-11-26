package com.hogent.androidproject.fake

import com.hogent.androidproject.network.ApiGame
import com.hogent.androidproject.network.GameApiService

class FakeGameApiService: GameApiService {
    override suspend fun getGames(platform: String, category: String): List<ApiGame> {
        return FakeDataSource.games
    }
}