package com.hogent.androidproject.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass.Companion.calculateFromSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.hogent.androidproject.R
import com.hogent.androidproject.ui.GamesApp
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val windowSizeClass = calculateFromSize(DpSize(100.dp,100.dp))
            GamesApp(windowSize = windowSizeClass,navController = navController)
        }
    }
    @Test
    fun verifyStartDestination() {
        Assert.assertEquals(
            NavigationRoutes.Start.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }

    @Test
    fun clickOnVolgendeGivesCategoryPage() {
        val text = composeTestRule.activity.getString(R.string.volgende)
        composeTestRule.onNodeWithText(text).performClick()
        Assert.assertEquals(
            NavigationRoutes.Category.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }
    @Test
    fun selectingPlatformAndCategoryGivesOverviewGames() {
        Assert.assertEquals(
            NavigationRoutes.Start.name,
            navController.currentBackStackEntry?.destination?.route
        )
        val text = composeTestRule.activity.getString(R.string.volgende)
        //Go to ctageory
        composeTestRule.onNodeWithText(text).performClick()
        Assert.assertEquals(
            NavigationRoutes.Category.name,
            navController.currentBackStackEntry?.destination?.route
        )
        //go to overview games
        composeTestRule.onNodeWithText(text).performClick()
        Assert.assertEquals(
            NavigationRoutes.List.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }
    @Test
    fun clickOnFavoritesGoToFavoritesList() {
        composeTestRule.onNodeWithContentDescription("Ga naar info pagina").performClick()
        Assert.assertEquals(
            NavigationRoutes.Favorites.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }
}