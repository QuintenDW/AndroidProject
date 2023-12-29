package com.hogent.androidproject.ui

import com.hogent.androidproject.fake.FakeApiGameRepository
import com.hogent.androidproject.ui.home.GameApiState
import com.hogent.androidproject.ui.home.GameViewModel
import com.hogent.androidproject.ui.home.WizardSteps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
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
        assertEquals(WizardSteps.PLATFORM,viewModel.gameUiState.value.wizardStep)
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
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel.createGameList()

        assertEquals(GameApiState.Success,viewModel.gameApiState)
        Dispatchers.resetMain()
    }

    @Test
    fun `Can go to category selection when on platform selection`() {
        viewModel.next()
        assertEquals(WizardSteps.CATEGORY,viewModel.gameUiState.value.wizardStep)
    }
    @Test
    fun `Can go to list step when on category selection`() {
        viewModel.next()
        viewModel.next()
        assertEquals(WizardSteps.LIST,viewModel.gameUiState.value.wizardStep)
    }
    @Test
    fun `Cannot go to next step when on list`() {
        viewModel.next()
        viewModel.next()
        assertThrows(IllegalStateException::class.java) { viewModel.next() }
    }
    @Test
    fun `Can go back to platform selection when on category selection`() {
        viewModel.next()
        assertEquals(WizardSteps.CATEGORY,viewModel.gameUiState.value.wizardStep)
        viewModel.back()
        assertEquals(WizardSteps.PLATFORM,viewModel.gameUiState.value.wizardStep)
    }
    @Test
    fun `Can go back to category step when on list selection`() {
        viewModel.next()
        viewModel.next()
        assertEquals(WizardSteps.LIST,viewModel.gameUiState.value.wizardStep)
        viewModel.back()
        assertEquals(WizardSteps.CATEGORY,viewModel.gameUiState.value.wizardStep)
    }
    @Test
    fun `Cannot go to back step when on platform`() {
        assertThrows(IllegalStateException::class.java) { viewModel.back() }
    }
}

class  TestDispatcherRule(private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}