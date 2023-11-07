package com.hogent.androidproject.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hogent.androidproject.navigation.NavigationRoutes

@Composable
fun BottomAppBarComponent(currentScreen: NavigationRoutes,modifier: Modifier = Modifier, goToStart: () -> Unit = {}, goToAbout: () -> Unit = {}) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier) {
        NavigationBarItem(selected = currentScreen == NavigationRoutes.Start,
            onClick = goToStart,
            icon = { Icon(
                imageVector = Icons.Outlined.Home,
                tint = if (currentScreen == NavigationRoutes.Start) Color.Black else Color.White,
                contentDescription = "Ga naar home pagina")
            }
        )
        NavigationBarItem(selected = currentScreen == NavigationRoutes.About,
            onClick = goToAbout,
            icon = { Icon(
                imageVector = Icons.Outlined.Info,
                tint = if (currentScreen == NavigationRoutes.About) Color.Black else Color.White,
                contentDescription = "Ga naar info pagina")
            }
        )
    }

}