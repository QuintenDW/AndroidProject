package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.R

/**
 * After selecting the platform the user has to select which category of game he/she likes to play
 */
@Composable
fun CategoryScreen(modifier: Modifier = Modifier,
                   categoryOptions: List<String>,
                   selectedOption: String,
                   onOptionChange: (String) -> Unit = {},
                   onButtonClicked: () -> Unit = {},
                   onCancelClicked: () -> Unit = {}
                   ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OptionsList(options = categoryOptions, selectedOption = selectedOption, onOptionChange = onOptionChange)

        Row {
            Button(onClick = onButtonClicked) {
                Text(stringResource(R.string.volgende))
            }
            Button(onClick = onCancelClicked) {
                Text(stringResource(R.string.annuleer))
            }
        }

    }
    
}