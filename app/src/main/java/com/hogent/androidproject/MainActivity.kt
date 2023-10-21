package com.hogent.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hogent.androidproject.ui.NavigationRoutes
import com.hogent.androidproject.ui.components.BottomAppBarComponent
import com.hogent.androidproject.ui.components.CategoryScreen
import com.hogent.androidproject.ui.components.StartScreen
import com.hogent.androidproject.ui.theme.AndroidprojectTheme

/**
 * Composable that show the top app bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier,navigateUp: () -> Unit = {},canNavigateBack: Boolean, currentScreen: NavigationRoutes) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(currentScreen.title))
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.terug_knop)
                    )
                }
            }
        }
    )
}
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val platformOptions = listOf("PC","Playstation","Xbox")
            val categoryOptions = listOf("mmorpg", "shooter", "strategy", "moba", "racing", "sports")
            val (selectedPlatform, onPlatformChange) = remember { mutableStateOf(platformOptions[0]) }
            val (selectedCategory, onCategoryChange) = remember { mutableStateOf(categoryOptions[0]) }
            AndroidprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentScreen = NavigationRoutes.valueOf(backStackEntry?.destination?.route ?: NavigationRoutes.Start.name)
                    Scaffold(
                        topBar = {
                            CustomTopAppBar(canNavigateBack = navController.previousBackStackEntry != null,
                                currentScreen = currentScreen,
                                navigateUp = { navController.navigateUp()}
                                )
                        },
                        bottomBar = {
                            BottomAppBarComponent()
                        },
                    ) { innerPadding ->
                        NavHost(navController = navController,
                            startDestination = NavigationRoutes.Start.name,
                            modifier = Modifier.padding(innerPadding),
                            ) {
                            composable(route = NavigationRoutes.Start.name) {
                                StartScreen(
                                    platformOptions = platformOptions,
                                    selectedOption = selectedPlatform,
                                    onOptionChange = onPlatformChange,
                                    onButtonClicked = { navController.navigate(NavigationRoutes.Category.name) }
                                )
                            }
                            composable(route = NavigationRoutes.Category.name) {
                                CategoryScreen(
                                    categoryOptions = categoryOptions,
                                    selectedOption = selectedCategory,
                                    onOptionChange = onCategoryChange,
                                    onButtonClicked = { navController.navigate(NavigationRoutes.List.name) },
                                    onCancelClicked = { navController.popBackStack(NavigationRoutes.Start.name,inclusive = false)}
                                )
                            }
                            composable(route = NavigationRoutes.List.name) {
                                Text(text = "List page")
                            }
                            composable(route = NavigationRoutes.About.name) {
                                Text(text = "about page")
                            }

                        }

                    }

                }
            }
        }
    }
}

@Composable
fun GreetingText(modifier: Modifier = Modifier,@StringRes headText: Int,@StringRes underText: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(5.dp), modifier = modifier.padding(12.dp).fillMaxWidth()) {
        Text(
            fontSize= 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = stringResource(headText),
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            fontSize= 16.sp,
            text = stringResource(underText),
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidprojectTheme {
        GreetingText(headText = R.string.welcome,underText = R.string.platform_selection)
    }
}