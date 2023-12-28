package com.hogent.androidproject.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hogent.androidproject.R
import com.hogent.androidproject.model.Game
import com.hogent.androidproject.navigation.NavigationType

@Composable
fun GamesScreen(windowSize: NavigationType,
                gameViewModel: GameViewModel = viewModel(factory = GameViewModel.Factory),
                addToFavorites: (Game) -> Unit,
                isFavorite: (Game) -> Boolean) {
    BackHandler(
        onBack = { gameViewModel.back() },
    )
    val gameUIState by gameViewModel.gameUiState.collectAsState()
    val platformOptions = listOf(
        stringResource(R.string.pc), stringResource(R.string.browser)
    )
    val categoryOptions = listOf(
        stringResource(R.string.mmorpg),
        stringResource(R.string.shooter),
        stringResource(R.string.strategy), stringResource(R.string.moba),
        stringResource(R.string.racing), stringResource(R.string.sports)
    )
    when(gameUIState.wizardStep) {
        WizardSteps.PLATFORM -> {
            StartScreen(
                platformOptions = platformOptions,
                onOptionChange = {  gameViewModel.setPlatform(it) },
                onButtonClicked = {
                    gameViewModel.next()
                },
                selectedOption=gameUIState.platform,
                windowSize = windowSize
            )
        }
        WizardSteps.CATEGORY -> {
            CategoryScreen(
                windowSize = windowSize,
                categoryOptions = categoryOptions,
                onOptionChange = { gameViewModel.setCategory(it) },
                onButtonClicked = {
                    gameViewModel.createGameList()
                    gameViewModel.next()
                },
                selectedOption= gameUIState.category,
                onCancelClicked = { gameViewModel.back()}
            )
        }

        WizardSteps.LIST -> {
            val listState by gameViewModel.gameUIListState.collectAsState()
            val gameApiState = gameViewModel.gameApiState
            GameListScreen(apiState = gameApiState,
                gameList = listState.gameList,
                onButtonClicked = { gameViewModel.back()},
                addToFavorites = addToFavorites,
                isFavorite = isFavorite)
        }
    }
}