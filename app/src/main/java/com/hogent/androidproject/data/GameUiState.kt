package com.hogent.androidproject.data

/**
 * Contains state for the different options you can select
 */
data class GameUiState(
    val platform: String = "PC",
    val category: String = "shooter"
)