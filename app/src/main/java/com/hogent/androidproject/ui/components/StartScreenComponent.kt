package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.GreetingText
import com.hogent.androidproject.R
/**
 * Starting screen where the user can select which platform he/she plays on
 */
@Composable
fun StartScreen(modifier: Modifier = Modifier, platformOptions: List<String>, selectedOption: String, onOptionChange: (String) -> Unit = {},
                onButtonClicked: () -> Unit = {} ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GreetingText()
        OptionsList(options = platformOptions , selectedOption = selectedOption, onOptionChange = onOptionChange)

        Button(onClick = onButtonClicked) {
            Text(stringResource(R.string.volgende))
        }
    }
}