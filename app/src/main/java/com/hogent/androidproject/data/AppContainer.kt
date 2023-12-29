package com.hogent.androidproject.data

import android.content.Context
import com.hogent.androidproject.data.database.GameDb
import com.hogent.androidproject.network.GameApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val gameRepository: GameRepository
}

/**
 * Container with all dependencies
 */
class DefaultAppContainer(private val context: Context): AppContainer {
    private val BASEURL =
        "https://www.freetogame.com/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASEURL)
        .build()
    private val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    override val gameRepository: GameRepository by lazy {
        CachingGameRepository(GameDb.getDatabase(context = context).gameDao(), retrofitService)
    }
}