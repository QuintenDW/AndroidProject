package com.hogent.androidproject.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hogent.androidproject.ui.components.NoFavorites
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import com.hogent.androidproject.ui.favorites.FavoritesScreen
import com.hogent.androidproject.ui.home.GameViewModel
import com.hogent.androidproject.ui.home.GamesScreen

@Composable
fun NavComponent(windowSize: NavigationType,innerPadding: PaddingValues, navController: NavHostController,
                 gameViewModel: GameViewModel = viewModel(factory = GameViewModel.Factory),
                 favoriteViewModel: FavoriteViewModel = viewModel()) {
    val favoriteUIState by favoriteViewModel.favoriteUIState.collectAsState()

    NavHost(navController = navController,
        startDestination = NavigationRoutes.Start.name,
        modifier = androidx.compose.ui.Modifier.padding(innerPadding),
        enterTransition = { enterScreen() },
        exitTransition = { exitScreen() }
    ) {
        composable(route = NavigationRoutes.Start.name) {
            GamesScreen(windowSize = windowSize)
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
