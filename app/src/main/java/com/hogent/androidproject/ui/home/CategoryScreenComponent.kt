package com.hogent.androidproject.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.hogent.androidproject.R
import com.hogent.androidproject.ui.components.GreetingText
import com.hogent.androidproject.ui.components.OptionsList


/**
 * After selecting the platform the user has to select which category of game he/she likes to play
 */
@Composable
fun CategoryScreen(categoryOptions: List<String>,
                   modifier: Modifier = Modifier,
                   onOptionChange: (String) -> Unit = {},
                   onButtonClicked: () -> Unit = {},
                   onCancelClicked: () -> Unit = {}
                   ) {
    var selectedOption by rememberSaveable { mutableStateOf("shooter") }
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
            GreetingText(headText = R.string.AppTitle,underText = R.string.category_selection)
            OptionsList(options = categoryOptions, selectedOption = selectedOption, onOptionChange = {
                selectedOption = it
                onOptionChange(it)
            })

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)), horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
            Button(modifier = Modifier.weight(1f),onClick = onCancelClicked) {
                Text(stringResource(R.string.annuleer),fontSize = 16.sp)
            }
            Button(modifier = Modifier.weight(1f),onClick = onButtonClicked) {
                Text(stringResource(R.string.volgende),fontSize = 16.sp)
            }
        }

    }
    
}