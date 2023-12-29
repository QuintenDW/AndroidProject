package com.hogent.androidproject.fake

import com.hogent.androidproject.data.GameRepository
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow

class FakeApiGameRepository: GameRepository {
    override fun getGames(platform: String, category: String): Flow<List<Game>> {
        return FakeGameApiService().getGamesAsFlow("","").asDomainObjects()
    }

    override fun getFavorites(): Flow<List<Game>> {
        return FakeGameApiService().getGamesAsFlow("","").asDomainObjects()
    }

    override suspend fun insertGame(game: Game) {
        //inserts a game
    }

    override suspend fun deleteGame(game: Game) {
        //Deletes game
    }

    override suspend fun updateFavorite(favorite: Favorite) {
        //updates to favorite
    }


    override suspend fun refresh(platform: String, category: String) {
        //refreshes to get games
    }

}