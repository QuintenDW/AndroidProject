package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * After selecting the platform the user has to select which category of game he/she likes to play
 */
@Composable
fun CategoryScreen(modifier: Modifier = Modifier,
                   categoryOptions: List<String>,
                   selectedOption: String,
                   onOptionChange: (String) -> Unit = {},
                   onButtonClicked: () -> Unit = {}
                   ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OptionsList(options = categoryOptions, selectedOption = selectedOption, onOptionChange = onOptionChange)
    }
    
}