package com.hogent.androidproject.data

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
class DefaultAppContainer(): AppContainer {
    private val BASE_URL =
        "https://www.freetogame.com/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    override val gameRepository: GameRepository by lazy {
        ApiGameRepository(retrofitService)
    }
}