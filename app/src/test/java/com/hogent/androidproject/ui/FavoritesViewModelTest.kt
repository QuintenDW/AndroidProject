package com.hogent.androidproject.ui

import com.hogent.androidproject.fake.FakeApiGameRepository
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import org.junit.Assert
import org.junit.Test

class FavoritesViewModelTest {
    private val viewModel = FavoriteViewModel(gameRepository = FakeApiGameRepository())


    @Test
    fun `Viewmodel starts with empty list of favorites`() {
        Assert.assertTrue(viewModel.favoriteUIState.value.favoriteGamesList.isEmpty())
    }
}
