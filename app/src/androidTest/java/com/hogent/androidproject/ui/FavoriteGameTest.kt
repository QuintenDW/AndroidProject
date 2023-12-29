package com.hogent.androidproject.ui

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hogent.androidproject.R
import com.hogent.androidproject.data.database.GameDao
import com.hogent.androidproject.data.database.GameDb
import com.hogent.androidproject.data.database.asDbGame
import com.hogent.androidproject.fake.FakeApiGameRepository
import com.hogent.androidproject.fake.FakeDataSource
import com.hogent.androidproject.navigation.NavigationRoutes
import com.hogent.androidproject.navigation.enterScreen
import com.hogent.androidproject.navigation.exitScreen
import com.hogent.androidproject.network.asDomainObjects
import com.hogent.androidproject.ui.components.NoFavorites
import com.hogent.androidproject.ui.favorites.FavoriteViewModel
import com.hogent.androidproject.ui.favorites.FavoritesScreen
import com.hogent.androidproject.ui.home.GameListScreen
import com.hogent.androidproject.ui.home.GameViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteGameTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val gameViewModel = GameViewModel(gameRepository = FakeApiGameRepository())
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var navController: TestNavHostController

    @Before
    fun initialize() {
        val gameDao: GameDao
        val gameDb: GameDb
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        gameDb = Room.inMemoryDatabaseBuilder(context, GameDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        gameDao = gameDb.gameDao()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            favoriteViewModel = FavoriteViewModel(gameRepository =
                FakeApiGameRepository(gameDao)
            )
            gameViewModel.createGameList()
            //Adding the 2 games to the fakedb so we can favorite them
            runBlocking {
                gameDao.insert(FakeDataSource.games.asDomainObjects()[0].asDbGame())
                gameDao.insert(FakeDataSource.games.asDomainObjects()[1].asDbGame())
            }

            val favoriteUIState by favoriteViewModel.favoriteUIState.collectAsState()

            NavHost(navController = navController,
                startDestination = NavigationRoutes.Start.name,
                modifier = Modifier.padding(PaddingValues(6.dp)),
                enterTransition = { enterScreen() },
                exitTransition = { exitScreen() }
            ) {
                composable(route = NavigationRoutes.Start.name) {
                    val listState by gameViewModel.gameUIListState.collectAsState()
                    val gameApiState = gameViewModel.gameApiState
                    GameListScreen(apiState = gameApiState,
                        gameList = listState.gameList,
                        onButtonClicked = { gameViewModel.back()},
                        addToFavorites = { favoriteViewModel.favoriteGame(it)})

                    //Button added to avoid illegalStateException with testing
                    Button(modifier = Modifier.testTag("GoFavorites"),
                        onClick = { navController.navigate(NavigationRoutes.Favorites.name)},
                        ) {
                        Text("Favorieten")
                    }
                }
                composable(route = NavigationRoutes.Favorites.name) {
                    favoriteUIState.favoriteGamesList
                    if (favoriteUIState.favoriteGamesList.isEmpty()) {
                        NoFavorites()
                    } else {
                        FavoritesScreen(favorites = favoriteUIState.favoriteGamesList,
                            goBack = {navController.navigate(NavigationRoutes.Start.name)},
                            addToFavorites = { favoriteViewModel.favoriteGame(it)}
                        )
                    }

                }

            }
        }
    }
    @Test
    fun showMessageWhenNoThereAreNoFavoriteGames() {
        val noFavorites = composeTestRule.activity.getString(R.string.geen_favorieten)
        composeTestRule.onNodeWithTag("GoFavorites").performClick()
        composeTestRule.onNodeWithTag(noFavorites).assertIsDisplayed()
    }

    @Test
    fun clickOnHeartIconWillAddGameToFavorites() {
        val text = composeTestRule.activity.getString(R.string.favoriete_knop)
        val favorites = composeTestRule.activity.getString(R.string.game_lijst)
        composeTestRule.onAllNodesWithTag(text)[0].performClick()
        composeTestRule.onNodeWithTag("GoFavorites").performClick()
        composeTestRule.onNodeWithTag(favorites).assertIsDisplayed()
    }

    @Test
    fun favoriteTwoGamesShowInListFavorites() {
        val text = composeTestRule.activity.getString(R.string.favoriete_knop)
        val favorites = composeTestRule.activity.getString(R.string.game_lijst)
        composeTestRule.onAllNodesWithTag(text)[0].performClick()
        composeTestRule.onAllNodesWithTag(text)[1].performClick()
        composeTestRule.onNodeWithTag("GoFavorites").performClick()
        composeTestRule.onNodeWithTag(favorites).assertIsDisplayed()
        //The 2 games from the fake data source
        composeTestRule.onNodeWithText("Overwatch 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("PUBG: BATTLEGROUNDS").assertIsDisplayed()
    }
}
