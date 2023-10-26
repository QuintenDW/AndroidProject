package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hogent.androidproject.GreetingText
import com.hogent.androidproject.R
/**
 * Starting screen where the user can select which platform he/she plays on
 */
@Composable
fun StartScreen(modifier: Modifier = Modifier, platformOptions: List<String>, onOptionChange: (String) -> Unit = {},
                onButtonClicked: () -> Unit = {} ) {

    var selectedOption by rememberSaveable { mutableStateOf("PC")}

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_medium)
        )) {
            GreetingText(headText = R.string.welcome,underText = R.string.platform_selection)
            OptionsList(
                options = platformOptions,
                selectedOption = selectedOption,
                onOptionChange = {
                    selectedOption = it
                    onOptionChange(it)
                }
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .weight(1f, false),horizontalArrangement = Arrangement.End) {
            Button(modifier = Modifier.widthIn(min= 200.dp),onClick = onButtonClicked) {
                Text(stringResource(R.string.volgende), fontSize = 16.sp)
            }
        }

    }
}