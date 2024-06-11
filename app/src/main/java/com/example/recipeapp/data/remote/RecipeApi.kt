package com.example.recipeapp.data.remote

import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.remote.response.RecipeDetailDto
import com.example.recipeapp.data.remote.response.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getPopularRecipes(
        @Query("sort") type: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getDietTypeRecipeList(
        @Query("diet") diet: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getRecipeByName(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 50,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getRecipesByLowCalories(
        @Query("maxCalories") maxCalories: Int = 200,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 15,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getRecipesByLowReadyTime(
        @Query("maxReadyTime") maxReadyTime: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 15,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getRecipesByHighProtein(
        @Query("minProtein") minProtein: Int = 40,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 15,
    ): RecipeListDto

    @GET("recipes/{id}/information")
    suspend fun getRecipesById(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeDetailDto

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
    }
}