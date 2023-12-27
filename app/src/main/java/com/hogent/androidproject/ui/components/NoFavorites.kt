package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hogent.androidproject.R

/**
 * Text that is shown when user has no favorite games.
 */
@Composable
fun NoFavorites(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().testTag(stringResource(R.string.geen_favorieten)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.geen_favorieten), fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}