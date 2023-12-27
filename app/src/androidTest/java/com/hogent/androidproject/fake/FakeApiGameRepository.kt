package com.hogent.androidproject.fake

import com.hogent.androidproject.data.GameRepository
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow

class FakeApiGameRepository: GameRepository {
    override fun getGames(platform: String, category: String): Flow<List<Game>> {
        return FakeGameApiService().getGamesAsFlow("","").asDomainObjects()
    }

    override suspend fun insertGame(game: Game) {
        //inserts a game
    }

    override fun gameExists(title: String): Boolean {
        return true
    }

    override suspend fun deleteGame(game: Game) {
        //Deletes game
    }

    override suspend fun updateGame(game: Game) {
        //updates a game
    }

    override suspend fun refresh(platform: String, category: String) {
        //refreshes to get games
    }

}