package com.hogent.androidproject.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination

@Composable
fun GamesNavigationRail(currentScreen: NavDestination?,
                        goToStart: () -> Unit = {},
                        goToFavorites: () -> Unit = {},
                        modifier: Modifier = Modifier) {
    NavigationRail(modifier = modifier,containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.primary,) {
        Spacer(modifier = Modifier.height(16.dp))
        NavigationRailItem(selected = currentScreen?.route == NavigationRoutes.Start.name,
            onClick = goToStart,
            icon = { Icon(
                imageVector = Icons.Outlined.Home,
                tint = if (currentScreen?.route == NavigationRoutes.Start.name) Color.Black else Color.White,
                contentDescription = "Ga naar home pagina")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavigationRailItem(selected = currentScreen?.route == NavigationRoutes.Favorites.name,
            onClick = goToFavorites,
            icon = { Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                tint = if (currentScreen?.route == NavigationRoutes.Favorites.name) Color.Black else Color.White,
                contentDescription = "Ga naar info pagina")
            }
        )
    }
}