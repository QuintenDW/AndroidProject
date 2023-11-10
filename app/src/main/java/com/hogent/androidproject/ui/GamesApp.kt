package com.hogent.androidproject.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hogent.androidproject.R
import com.hogent.androidproject.navigation.CustomTopAppBar
import com.hogent.androidproject.navigation.NavigationRoutes
import com.hogent.androidproject.ui.components.BottomAppBarComponent
import com.hogent.androidproject.ui.components.CategoryScreen
import com.hogent.androidproject.ui.components.FavoritesScreen
import com.hogent.androidproject.ui.components.GameListScreen
import com.hogent.androidproject.ui.components.NoFavorites
import com.hogent.androidproject.ui.components.StartScreen
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import com.hogent.androidproject.ui.home.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesApp(gameViewModel: GameViewModel = viewModel(), favoriteViewModel: FavoriteViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationRoutes.valueOf(backStackEntry?.destination?.route ?: NavigationRoutes.Start.name)
    val gameUIState by gameViewModel.gameUiState.collectAsState()
    val favoriteUIState by favoriteViewModel.favoriteUIState.collectAsState()
    val platformOptions = listOf(stringResource(R.string.pc), stringResource(R.string.browser),
        stringResource(R.string.all)
    )
    val categoryOptions = listOf(stringResource(R.string.mmorpg),
        stringResource(R.string.shooter),
        stringResource(R.string.strategy), stringResource(R.string.moba),
        stringResource(R.string.racing), stringResource(R.string.sports)
    )
    Scaffold(
        topBar = {
            CustomTopAppBar(canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp()}
            )
        },
        bottomBar = {
            BottomAppBarComponent(
                currentScreen = currentScreen,
                goToStart = { navController.navigate(NavigationRoutes.Start.name)},
                goToFavorites = { navController.navigate(NavigationRoutes.Favorites.name)} )
        },
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = NavigationRoutes.Start.name,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { enterScreen() },
            exitTransition = { exitScreen() }
        ) {
            composable(route = NavigationRoutes.Start.name) {
                StartScreen(
                    platformOptions = platformOptions,
                    onOptionChange = {  gameViewModel.setPlatform(it) },
                    onButtonClicked = {

                        navController.navigate(NavigationRoutes.Category.name) }
                )
            }
            composable(route = NavigationRoutes.Category.name) {
                CategoryScreen(
                    categoryOptions = categoryOptions,
                    onOptionChange = { gameViewModel.setCategory(it) },
                    onButtonClicked = {
                        gameViewModel.createGameList()
                        navController.navigate(NavigationRoutes.List.name)
                    },
                    onCancelClicked = { navController.popBackStack(NavigationRoutes.Start.name,inclusive = false)}
                )
            }
            composable(route = NavigationRoutes.List.name) {
                GameListScreen(gameViewModel = gameViewModel, favoriteViewModel = favoriteViewModel)
            }
            composable(route = NavigationRoutes.Favorites.name) {
                if (favoriteUIState.favoriteGamesList.isEmpty()) {
                    NoFavorites()
                } else {
                    FavoritesScreen(favoriteViewModel = favoriteViewModel)
                }

            }

        }

    }
}
/**
 * Animation for screen that enters
 */
fun enterScreen() : EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    )
}
/**
 * Animation for leaving screen
 */
fun exitScreen() : ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    )
}
