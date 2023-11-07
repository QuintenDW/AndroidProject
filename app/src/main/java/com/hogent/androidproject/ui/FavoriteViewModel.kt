package com.hogent.androidproject.ui

import androidx.lifecycle.ViewModel
import com.hogent.androidproject.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel : ViewModel() {
    private val _favoriteUiState = MutableStateFlow(GameUiState())
    val gameUiState = _favoriteUiState.asStateFlow()
}