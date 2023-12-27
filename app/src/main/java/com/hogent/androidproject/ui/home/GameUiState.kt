package com.hogent.androidproject.ui.home

import com.hogent.androidproject.model.Game

/**
 * Contains state for the different options you can select
 */
data class GameUiState(
    val wizardStep: WizardSteps = WizardSteps.PLATFORM,
    val platform: String = "PC",
    val category: String = "shooter",

)

data class GameListState(val gameList: List<Game> = listOf())

sealed interface GameApiState {
    object Success : GameApiState
    object Error : GameApiState
    object Loading : GameApiState
}