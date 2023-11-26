package com.hogent.androidproject.network

import retrofit2.http.GET
import retrofit2.http.Query


interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("platform") platform: String,@Query("category") category: String): List<ApiGame>
}
