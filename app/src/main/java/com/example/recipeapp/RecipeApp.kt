package com.example.recipeapp

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
@HiltAndroidApp
class RecipeApp: Application(), Configuration.Provider {


    @Inject lateinit var workerConfiguration: Configuration
    override val workManagerConfiguration: Configuration
        get() = workerConfiguration
}