package com.hogent.androidproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: DbGame)

    @Update(entity = DbGame::class)
    suspend fun update(favorite: DbFavorite)

    @Query("SELECT * from games where lower(platform) like '%' || :platform || '%' and lower(genre) like :genre ORDER BY title ASC")
    fun getAllGames(platform: String, genre: String): Flow<List<DbGame>>

    @Query("SELECT * from games where isFavorite is 1")
    fun getFavoriteGames(): Flow<List<DbGame>>
}