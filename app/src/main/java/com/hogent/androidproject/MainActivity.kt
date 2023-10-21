package com.hogent.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hogent.androidproject.ui.NavigationRoutes
import com.hogent.androidproject.ui.components.BottomAppBarComponent
import com.hogent.androidproject.ui.components.CategoryScreen
import com.hogent.androidproject.ui.components.StartScreen
import com.hogent.androidproject.ui.theme.AndroidprojectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val platformOptions = listOf("PC","Playstation","Xbox")
            val categoryOptions = listOf("mmorpg", "shooter", "strategy", "moba", "racing", "sports")
            var (selectedPlatform, onPlatformChange) = remember { mutableStateOf(platformOptions[0]) }
            var (selectedCategory, onCategoryChange) = remember { mutableStateOf(categoryOptions[0]) }
            AndroidprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    Text("Top app bar")
                                }
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
                                )
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
fun GreetingText(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(5.dp), modifier = modifier.padding(12.dp)) {
        Text(
            fontSize= 24.sp,
            text = stringResource(R.string.welcome),
            modifier = modifier
        )
        Text(
            fontSize= 16.sp,
            text = stringResource(R.string.platform_selection),
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidprojectTheme {
        GreetingText()
    }
}