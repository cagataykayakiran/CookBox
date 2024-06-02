package com.example.recipeapp.data.remote

import com.example.recipeapp.data.remote.response.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getBreakfastRecipeList(
        @Query("type") type: String = "Breakfast",
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeInstructions") addRecipeInstructions: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getDietTypeRecipeList(
        @Query("diet") diet: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeInstructions") addRecipeInstructions: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY,
    ): RecipeListDto

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val API_KEY = "d106ce56348142098059f8bc48332177"
    }
}