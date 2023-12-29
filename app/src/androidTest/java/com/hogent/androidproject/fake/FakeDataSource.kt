package com.hogent.androidproject.fake

import com.hogent.androidproject.network.ApiGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeDataSource {
    val games =  listOf(
    ApiGame(id = 540,title="Overwatch 2",thumbnail="https://www.freetogame.com/g/540/thumbnail.jpg", gameURL = "https://www.freetogame.com/open/overwatch-2",
        developer = "Activision Blizzard", releaseDate = "2022-10-04",
        description="A hero-focused first-person team shooter from Blizzard Entertainment.",
        genre = "Shooter",
        platform = "PC (Windows)",
        publisher = "Activision Blizzard",
        profileURL = "https://www.freetogame.com/overwatch-2"
    ) ,
    ApiGame(id = 516,title="PUBG: BATTLEGROUNDS",thumbnail="https://www.freetogame.com/g/516/thumbnail.jpg",
        gameURL = "https://www.freetogame.com/open/pubg",
        developer = "KRAFTON, Inc.", releaseDate = "2022-01-12",
        description="Get into the action in one of the longest running battle royale games PUBG Battlegrounds.",
        genre = "Shooter",
        platform = "PC (Windows)",
        publisher = "KRAFTON, Inc.",
        profileURL = "https://www.freetogame.com/pubg"
    )
    )
    var favorites = arrayListOf<ApiGame>()
}
fun FakeDataSource.getGamesAsFlow(): Flow<List<ApiGame>> = flow {
    emit(favorites)
}