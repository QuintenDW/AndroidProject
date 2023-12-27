package com.hogent.androidproject.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hogent.androidproject.R
import com.hogent.androidproject.fake.FakeApiGameRepository
import com.hogent.androidproject.navigation.NavigationType
import com.hogent.androidproject.ui.home.GameViewModel
import com.hogent.androidproject.ui.home.GamesScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameWizardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val viewModel = GameViewModel(gameRepository = FakeApiGameRepository())

    @Before
    fun initialize() {
        composeTestRule.setContent {
            GamesScreen(windowSize = NavigationType.COMPACT_NAVIGATION,
                gameViewModel = viewModel, addToFavorites = {}, isFavorite = {false})
        }
    }
    @Test
    fun wizardStartsWithPlatformSelection() {
        val greetingText = composeTestRule.activity.getString(R.string.platform_selection)
        composeTestRule.onNodeWithText(greetingText).assertIsDisplayed()
    }
    @Test
    fun clickOnPcButtonAndGoToNextWillShowCategorySelection() {
        val text = composeTestRule.activity.getString(R.string.pc)
        val next = composeTestRule.activity.getString(R.string.volgende)
        val greetingText = composeTestRule.activity.getString(R.string.category_selection)
        composeTestRule.onNodeWithTag(text).performClick()
        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithText(greetingText).assertIsDisplayed()
    }

    @Test
    fun clickOnShooterButtonAndGoToNextWillShowGameList() {
        val text = composeTestRule.activity.getString(R.string.shooter)
        val next = composeTestRule.activity.getString(R.string.volgende)
        val list = composeTestRule.activity.getString(R.string.game_lijst)
        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithTag(text).performClick()
        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithTag(list).assertIsDisplayed()
    }
    @Test
    fun clickOnBackButtonOnSelectionScreenWillGoToPlatformSelection() {
        val next = composeTestRule.activity.getString(R.string.volgende)
        val back = composeTestRule.activity.getString(R.string.annuleer)
        val greetingText = composeTestRule.activity.getString(R.string.platform_selection)

        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithTag(back).performClick()
        composeTestRule.onNodeWithText(greetingText).assertIsDisplayed()
    }
    @Test
    fun clickOnBackButtonOnListScreenWillGoToCategorySelection() {
        val next = composeTestRule.activity.getString(R.string.volgende)
        val list = composeTestRule.activity.getString(R.string.game_lijst)
        val back = composeTestRule.activity.getString(R.string.annuleer)
        val greetingText = composeTestRule.activity.getString(R.string.category_selection)
        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithTag(next).performClick()
        composeTestRule.onNodeWithTag(list).assertIsDisplayed()
        composeTestRule.onNodeWithTag(back).performClick()
        composeTestRule.onNodeWithText(greetingText).assertIsDisplayed()
    }
}