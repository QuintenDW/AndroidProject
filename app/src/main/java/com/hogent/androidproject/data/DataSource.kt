package com.hogent.androidproject.data

import com.hogent.androidproject.model.Game

class DataSource() {
    fun loadGames(): List<Game> {
        return listOf<Game>(
            Game("Overwatch 2",
                "A hero-focused first-person team shooter from Blizzard Entertainment.",
                "Shooter",
                "Playstation",
                "Activision Blizzard"
                ) ,
            Game("PUBG: BATTLEGROUNDS",
            "Get into the action in one of the longest running battle royale games PUBG Battlegrounds.",
            "Shooter",
            "PC (Windows)",
            "KRAFTON, Inc."
        )
        )
    }
}