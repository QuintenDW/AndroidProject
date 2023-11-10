package com.hogent.androidproject.ui

import com.hogent.androidproject.ui.home.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun `Viewmodel starts with default values`() {
        assertEquals("PC",viewModel.gameUiState.value.platform)
        assertEquals("shooter",viewModel.gameUiState.value.category)
        assertTrue(viewModel.gameUiState.value.gameList.isEmpty())
    }

    @Test
    fun `Can set platform`() {
        viewModel.setPlatform("All")
        assertEquals("All",viewModel.gameUiState.value.platform)
    }
    @Test
    fun `Can set Category`() {
        viewModel.setCategory("mmorpg")
        assertEquals("mmorpg",viewModel.gameUiState.value.category)
    }

}