package com.hogent.androidproject.ui

import com.hogent.androidproject.fake.FakeApiGameRepository
import com.hogent.androidproject.fake.FakeDataSource
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.ui.home.GameApiState
import com.hogent.androidproject.ui.home.GameViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class GameViewModelTest {
    private val viewModel = GameViewModel(gameRepository = FakeApiGameRepository())

    @get:Rule
    val testDispatcher = TestDispatcherRule()
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

    @Test
    fun `Can create games list`() {
        viewModel.createGameList()
        assertEquals(FakeDataSource.games.asDomainObjects(),viewModel.gameUiState.value.gameList)
        assertEquals(GameApiState.Success(viewModel.gameUiState.value.gameList),viewModel.gameApiState)
    }
}

class  TestDispatcherRule(val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}