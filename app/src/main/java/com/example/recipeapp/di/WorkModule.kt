package com.example.recipeapp.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.work_manager.DataSyncRepository
import com.example.recipeapp.work_manager.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkModule {

    @Provides
    @Singleton
    internal fun providesWorkManager(
        @ApplicationContext context: Context
    ): WorkManager = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        workerFactory: HiltWorkerFactory
    ): Configuration {
        return Configuration.Builder().apply {
            setWorkerFactory(workerFactory)
        }.build()
    }

    @Provides
    @Singleton
    fun providesDataSyncRepository(
        recipeRepository: RecipeRepository,
    ): DataSyncRepository {
        return DataSyncRepository(repository = recipeRepository)
    }

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}