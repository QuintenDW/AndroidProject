package com.hogent.androidproject.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hogent.androidproject.navigation.CustomTopAppBar
import com.hogent.androidproject.navigation.GamesNavigationRail
import com.hogent.androidproject.navigation.GamesPermanentNavigationDrawer
import com.hogent.androidproject.navigation.NavComponent
import com.hogent.androidproject.navigation.NavigationRoutes
import com.hogent.androidproject.navigation.NavigationType
import com.hogent.androidproject.navigation.BottomAppBarComponent

@Composable
fun GamesApp(windowSize: NavigationType,  navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationRoutes.valueOf(backStackEntry?.destination?.route ?: NavigationRoutes.Start.name)
    val goToStart = { navController.navigate(NavigationRoutes.Start.name) {launchSingleTop=true} }
    val goToFavorites = { navController.navigate(NavigationRoutes.Favorites.name) {launchSingleTop=true}}
    when (windowSize) {
        NavigationType.COMPACT_NAVIGATION -> {
            Scaffold(
                topBar = {
                    CustomTopAppBar(canNavigateBack = navController.previousBackStackEntry != null,
                        currentScreen = currentScreen,
                        navigateUp = { navController.navigateUp() }
                    )
                },
                bottomBar = {
                    BottomAppBarComponent(
                        currentScreen = currentScreen,
                        goToStart = goToStart,
                        goToFavorites = goToFavorites)
                },
            ) { innerPadding ->
                NavComponent(windowSize = windowSize,innerPadding = innerPadding,navController = navController)
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            Row {
                GamesNavigationRail(currentScreen = backStackEntry?.destination,
                    goToStart = goToStart,
                    goToFavorites = goToFavorites)
                Scaffold(
                    topBar = {
                        CustomTopAppBar(canNavigateBack = navController.previousBackStackEntry != null,
                            currentScreen = currentScreen,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                ) { innerPadding ->
                    NavComponent(windowSize = windowSize,innerPadding = innerPadding,navController = navController)
                }
            }
        }
        else -> {
            GamesPermanentNavigationDrawer(currentScreen = backStackEntry?.destination,
                goToStart = goToStart,
                goToFavorites = goToFavorites) {
                Scaffold(
                    topBar = {
                        CustomTopAppBar(canNavigateBack = navController.previousBackStackEntry != null,
                            currentScreen = currentScreen,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                ) { innerPadding ->
                    NavComponent(windowSize = windowSize,innerPadding = innerPadding,navController = navController)
                }
            }
        }
    }

}
