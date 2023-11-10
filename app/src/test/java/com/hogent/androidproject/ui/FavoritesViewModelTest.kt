package com.hogent.androidproject.ui

import com.hogent.androidproject.model.Game
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import org.junit.Assert
import org.junit.Test

class FavoritesViewModelTest {
    private val viewModel = FavoriteViewModel()

    @Test
    fun `Viewmodel starts with empty list of favorites`() {
        Assert.assertTrue(viewModel.favoriteUIState.value.favoriteGamesList.isEmpty())
    }
    @Test
    fun `Add new game to favorites`() {
        val testgame = Game("nieuwe game","dit is een nieuwe game","shooter","pc","Quinten De Wolf")
        viewModel.favoriteGame(testgame)
        Assert.assertTrue(viewModel.favoriteUIState.value.favoriteGamesList.contains(testgame))
    }
    @Test
    fun `remove new game from favorites`() {
        val testgame = Game("nieuwe game","dit is een nieuwe game","shooter","pc","Quinten De Wolf")
        viewModel.favoriteGame(testgame)
        viewModel.favoriteGame(testgame)
        Assert.assertFalse(viewModel.favoriteUIState.value.favoriteGamesList.contains(testgame))
    }
}