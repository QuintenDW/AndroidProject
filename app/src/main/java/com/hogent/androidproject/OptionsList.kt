package com.hogent.androidproject

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsList(modifier: Modifier = Modifier,options: List<String>,selectedOption: String,onOptionChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,modifier = modifier) {
        options.forEach { text ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (text == selectedOption),
                    onClick = { onOptionChange(text) }
                )) {
                RadioButton(selected = (text == selectedOption), onClick = {
                    onOptionChange(text)
                })
                Text(text = text, modifier = Modifier.padding(8.dp))
            }
        }
    }
}