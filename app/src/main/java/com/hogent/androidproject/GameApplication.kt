package com.hogent.androidproject

import android.app.Application
import com.hogent.androidproject.data.AppContainer
import com.hogent.androidproject.data.DefaultAppContainer

class GameApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}