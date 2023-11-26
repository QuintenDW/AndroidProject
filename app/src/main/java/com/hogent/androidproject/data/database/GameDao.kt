package com.hogent.androidproject.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: dbGame)

    @Update
    suspend fun update(game: dbGame)

    @Delete
    suspend fun delete(game: dbGame)

    @Query("SELECT * from games where lower(platform) like '%' || :platform || '%' and lower(genre) like :genre ORDER BY title ASC")
    fun getAllGames(platform: String, genre: String): Flow<List<dbGame>>

    @Query("DELETE from games")
    suspend fun reset()
}