package com.hogent.androidproject.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.hogent.androidproject.R

/**
 * Composable for navigation drawer
 */
@Composable
fun GamesPermanentNavigationDrawer(currentScreen: NavDestination?,
                                   goToStart: () -> Unit = {},
                                   goToFavorites: () -> Unit = {},
                                   content: @Composable () -> Unit) {
    PermanentNavigationDrawer(drawerContent = {
        PermanentDrawerSheet(
            modifier = Modifier.width(dimensionResource(id = R.dimen.drawer_width)),
            drawerContainerColor = MaterialTheme.colorScheme.tertiary,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            NavigationDrawerItem(selected = currentScreen?.route == NavigationRoutes.Start.name,
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.tertiary,
                    selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                onClick = goToStart,
                label = {Text(
                    text ="Home",
                    color = if (currentScreen?.route == NavigationRoutes.Start.name)
                        Color.Black else Color.White,
                )},
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        tint = if (currentScreen?.route == NavigationRoutes.Start.name) Color.Black else Color.White,
                        contentDescription = stringResource(id = R.string.home_pagina)
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NavigationDrawerItem(selected = currentScreen?.route == NavigationRoutes.Favorites.name,
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.tertiary,
                    selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                onClick = goToFavorites,
                label = {
                    Text(text =
                    "Favorieten",
                        color = if (currentScreen?.route == NavigationRoutes.Favorites.name)
                            Color.Black else Color.White,

                )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        tint = if (currentScreen?.route == NavigationRoutes.Favorites.name) Color.Black else Color.White,
                        contentDescription = stringResource(id = R.string.favorieten_pagina)
                    )
                }
            )
        }
    }
    ) {
        content()
    }
}