package com.hogent.androidproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class of games
 */
@Database(entities = [dbGame::class], version=2, exportSchema = false)
abstract class GameDb : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var Instance: GameDb? = null

        fun getDatabase(context: Context): GameDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GameDb::class.java, "game_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}