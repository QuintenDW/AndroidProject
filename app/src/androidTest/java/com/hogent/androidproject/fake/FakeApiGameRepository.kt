package com.hogent.androidproject.fake

import com.hogent.androidproject.data.GameRepository
import com.hogent.androidproject.data.database.GameDao
import com.hogent.androidproject.data.database.asDbFavorite
import com.hogent.androidproject.data.database.asDomainGames
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeApiGameRepository(private val gameDao: GameDao? = null): GameRepository {
    override fun getGames(platform: String, category: String): Flow<List<Game>> {
        return FakeGameApiService().getGamesAsFlow("","").asDomainObjects()
    }

    override fun getFavorites(): Flow<List<Game>> {
        return gameDao?.getFavoriteGames()?.map {
            it.asDomainGames()
        } ?: FakeDataSource.getGamesAsFlow().asDomainObjects()
    }

    override suspend fun insertGame(game: Game) {
        //inserts a game
    }



    override suspend fun updateFavorite(favorite: Favorite) {
        gameDao?.update(favorite.asDbFavorite())

    }


    override suspend fun refresh(platform: String, category: String) {
        //refreshes to get games
    }

}