package com.hogent.androidproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomAppBarComponent(modifier: Modifier = Modifier) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier,

        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Home,contentDescription = "Go to home")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Info,contentDescription = "Go to about")
            }
        })
}