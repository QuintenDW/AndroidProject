package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.GreetingText

@Composable
fun StartScreen(modifier: Modifier = Modifier, platformOptions: List<String>, selectedOption: String, onOptionChange: (String) -> Unit ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GreetingText()
        OptionsList(options = platformOptions , selectedOption = selectedOption, onOptionChange = onOptionChange)
    }
}