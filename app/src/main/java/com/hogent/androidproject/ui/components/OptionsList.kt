package com.hogent.androidproject.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hogent.androidproject.navigation.NavigationType

/**
 * Reusable radio button list used for platform choice and category choice
 */
@Composable
fun OptionsList(windowSize: NavigationType, options: List<String>, selectedOption: String, modifier: Modifier = Modifier, onOptionChange: (String) -> Unit) {
    when (windowSize) {
        NavigationType.COMPACT_NAVIGATION ->
            LazyColumn(modifier = modifier) {
            items(options) { text ->
                Row( verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionChange(text) }
                    )) {
                    RadioButton(selected = (text == selectedOption), onClick = {
                        onOptionChange(text)
                    })
                    Text(text = text)
                }
            }
        }
        else ->
            LazyRow(modifier = modifier) {
                items(options) { text ->
                    Row( verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 20.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionChange(text) }
                        )) {
                        RadioButton(selected = (text == selectedOption), onClick = {
                            onOptionChange(text)
                        })
                        Text(text = text)
                    }
                }
            }
    }

}