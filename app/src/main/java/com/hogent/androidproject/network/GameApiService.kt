package com.hogent.androidproject.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Query


interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("platform") platform: String,@Query("category") category: String): List<ApiGame>
}

/**
 * Helper functions used by refresh()
 */
fun GameApiService.getGamesAsFlow(platform: String,category: String): Flow<List<ApiGame>> = flow {
    emit(getGames(platform,category))
}