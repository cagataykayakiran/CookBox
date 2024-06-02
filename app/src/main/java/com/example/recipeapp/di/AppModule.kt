package com.example.recipeapp.di

import android.content.Context
import com.example.myapplication.util.NetworkHelper
import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.RecipeApi.Companion.BASE_URL
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(api: RecipeApi): RecipeRepository {
        return RecipeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper {
        return NetworkHelper(appContext)
    }
}