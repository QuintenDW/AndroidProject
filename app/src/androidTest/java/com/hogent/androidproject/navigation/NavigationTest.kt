package com.hogent.androidproject.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
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

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            GamesApp(windowSize = NavigationType.COMPACT_NAVIGATION,navController = navController)
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
    fun clickOnFavoritesGoToFavoritesList() {
    val text = composeTestRule.activity.getString(R.string.favorieten_pagina)
        composeTestRule.onNodeWithContentDescription(text).performClick()
        Assert.assertEquals(
            NavigationRoutes.Favorites.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }
}