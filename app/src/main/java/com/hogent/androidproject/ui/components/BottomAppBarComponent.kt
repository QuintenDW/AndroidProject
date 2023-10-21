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
import com.hogent.androidproject.ui.NavigationRoutes

@Composable
fun BottomAppBarComponent(modifier: Modifier = Modifier, goToStart: () -> Unit = {}, goToAbout: () -> Unit = {},currentScreen: NavigationRoutes) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier) {
        NavigationBarItem(selected = currentScreen == NavigationRoutes.Start,
            onClick = goToStart,
            icon = { Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Ga naar home pagina")
            }
        )
        NavigationBarItem(selected = currentScreen == NavigationRoutes.About,
            onClick = goToAbout,
            icon = { Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Ga naar info pagina")
            }
        )
    }

}