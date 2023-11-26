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

    @Query("SELECT * from games ORDER BY title ASC")
    fun getAllItems(): Flow<List<dbGame>>
}