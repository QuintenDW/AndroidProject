package com.hogent.androidproject.data

import com.hogent.androidproject.data.database.GameDao
import com.hogent.androidproject.data.database.asDbFavorite
import com.hogent.androidproject.data.database.asDbGame
import com.hogent.androidproject.data.database.asDomainGames
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.GameApiService
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GameRepository {
    fun getGames(platform: String, category: String): Flow<List<Game>>

    fun getFavorites(): Flow<List<Game>>
    suspend fun insertGame(game: Game)

    suspend fun updateFavorite(favorite: Favorite)

    suspend fun refresh(platform: String, category: String)

}


class CachingGameRepository(private val gameDao: GameDao,private val gameApiService: GameApiService): GameRepository {
    /**
     * Gets all games for a given platform and genre
     */
    override fun getGames(platform: String, category: String): Flow<List<Game>> {
        return gameDao.getAllGames(platform,category).map {
            it.asDomainGames()
        }.onEach {
            if (it.isEmpty()) {
                refresh(platform,category)
            }
        }
    }

    /**
     * Gets the users favorite games (can be empty if user has no favorites)
     */
    override fun getFavorites(): Flow<List<Game>> {
        return gameDao.getFavoriteGames().map {
            it.asDomainGames()
        }
    }

    override suspend fun insertGame(game: Game) {
        gameDao.insert(game.asDbGame())
    }


    // If the target entity is specified via entity then the parameters can be of arbitrary
    // POJO types that will be interpreted as partial entities.
    override suspend fun updateFavorite(favorite: Favorite) {
        gameDao.update(favorite.asDbFavorite())
    }

    /**
     * Will get games from api
     */
    override suspend fun refresh(platform: String, category: String) {
        gameApiService.getGamesAsFlow(platform,category).asDomainObjects().collect {
            value ->
            for(game in value) {
                insertGame(game)
            }
        }
    }


}