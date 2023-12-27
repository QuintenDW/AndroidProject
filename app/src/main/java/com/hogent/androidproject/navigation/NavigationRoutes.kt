package com.hogent.androidproject.navigation

import androidx.annotation.StringRes
import com.hogent.androidproject.R

enum class NavigationRoutes(@StringRes val title: Int) {
    Start(title = R.string.start),
    Favorites(title = R.string.favorieten),
}