package com.hogent.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hogent.androidproject.navigation.NavigationType
import com.hogent.androidproject.ui.GamesApp
import com.hogent.androidproject.ui.components.GreetingText
import com.hogent.androidproject.ui.theme.AndroidprojectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            AndroidprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (windowSizeClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            GamesApp(windowSize = NavigationType.COMPACT_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            GamesApp(windowSize = NavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            GamesApp(windowSize = NavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            GamesApp(windowSize = NavigationType.COMPACT_NAVIGATION)
                        }
                    }
                    
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidprojectTheme {
        GreetingText(headText = R.string.welcome,underText = R.string.platform_selection)
    }
}