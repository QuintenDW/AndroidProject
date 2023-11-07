package com.hogent.androidproject.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingText(modifier: Modifier = Modifier, @StringRes headText: Int, @StringRes underText: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(5.dp), modifier = modifier
        .padding(12.dp)
        .fillMaxWidth()) {
        Text(
            fontSize= 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = stringResource(headText),
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            fontSize= 16.sp,
            text = stringResource(underText),
            modifier = modifier
        )
    }

}