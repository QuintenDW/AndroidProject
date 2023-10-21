package com.hogent.androidproject.ui

import androidx.annotation.StringRes
import com.hogent.androidproject.R

enum class NavigationRoutes(@StringRes val title: Int) {
    Start(title = R.string.start),
    Category(title = R.string.categorie),
    List(title = R.string.overzicht),
    About(title = R.string.over),
}