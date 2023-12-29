package com.hogent.androidproject.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hogent.androidproject.data.database.GameDao
import com.hogent.androidproject.data.database.GameDb
import com.hogent.androidproject.data.database.asDbFavorite
import com.hogent.androidproject.data.database.asDbGame
import com.hogent.androidproject.data.database.asDomainGame
import com.hogent.androidproject.model.Favorite
import com.hogent.androidproject.model.Game
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GameDaoTest {

    private lateinit var gameDao: GameDao
    private lateinit var gameDb: GameDb

    private var game = Game(11000,"test game","Dit is een test game","shooter","browser","Quinten DW",false)
    private var game2 = Game(11001,"test game 2","Dit is een test game","shooter","browser","Quinten DW",false)

    private var favorite = Favorite(11000,true)
    private suspend fun addGameToDb() {
        gameDao.insert(game.asDbGame())
    }

    private suspend fun addGamesToDb() {
        gameDao.insert(game.asDbGame())
        gameDao.insert(game2.asDbGame())
    }

    private suspend fun favoriteFirstGame() {
        gameDao.update(favorite.asDbFavorite())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        gameDb = Room.inMemoryDatabaseBuilder(context, GameDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        gameDao = gameDb.gameDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        gameDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertOneGame() = runBlocking{
        addGameToDb()
        val allItems = gameDao.getAllGames("browser","shooter").first()
        assertEquals(allItems[0].asDomainGame(), game)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertMultipleGame() = runBlocking{
        addGamesToDb()
        val allItems = gameDao.getAllGames("browser","shooter").first()
        assertEquals(allItems[0].asDomainGame(), game)
        assertEquals(allItems[1].asDomainGame(), game2)
    }

    @Test
    @Throws(Exception::class)
    fun daUpdateToFavorite() = runBlocking{
        addGamesToDb()
        favoriteFirstGame()
        val allItems = gameDao.getAllGames("browser","shooter").first()
        assertTrue(allItems[0].asDomainGame().isFavorite)
        assertFalse(allItems[1].asDomainGame().isFavorite)
    }
}