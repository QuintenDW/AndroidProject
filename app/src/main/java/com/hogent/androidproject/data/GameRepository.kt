package com.hogent.androidproject.data

import com.hogent.androidproject.data.database.GameDao
import com.hogent.androidproject.data.database.asDbGame
import com.hogent.androidproject.data.database.asDomainGames
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.network.GameApiService
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GameRepository {
    fun getGames(platform: String, category: String): Flow<List<Game>>
    suspend fun insertGame(game: Game)

    fun gameExists(title: String): Boolean

    suspend fun deleteGame(game: Game)

    suspend fun updateGame(game: Game)

    suspend fun refresh(platform: String, category: String)

}


class CachingGameRepository(private val gameDao: GameDao,private val gameApiService: GameApiService): GameRepository {
    override fun getGames(platform: String, category: String): Flow<List<Game>> {
        return gameDao.getAllGames(platform,category).map {
            it.asDomainGames()
        }.onEach {
            if (it.isEmpty()) {
                refresh(platform,category)
            }
        }
    }

    override suspend fun insertGame(game: Game) {
        gameDao.insert(game.asDbGame())
    }

    override fun gameExists (title: String): Boolean {
        return gameDao.gameExists(title)
    }

    override suspend fun deleteGame(game: Game) {
        gameDao.delete(game.asDbGame())
    }

    override suspend fun updateGame(game: Game) {
        gameDao.update(game.asDbGame())
    }

    /**
     * Will get games from api
     */
    override suspend fun refresh(platform: String, category: String) {
        gameApiService.getGamesAsFlow(platform,category).asDomainObjects().collect {
            value ->
            for(game in value) {
                //if (!gameExists(game.title)) {
                    insertGame(game);
                //}

            }
        }
    }


}