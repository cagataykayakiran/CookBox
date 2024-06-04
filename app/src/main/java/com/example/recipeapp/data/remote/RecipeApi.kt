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

    @GET("recipes/complexSearch")
    suspend fun getRecipeByName(
        @Query("query") query: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeInstructions") addRecipeInstructions: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("number") number: Int = 50,
    ): RecipeListDto

    @GET("recipes/complexSearch")
    suspend fun getRecipesByLowCalories(
        @Query("maxCalories") maxCalories: Int = 40,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeInstructions") addRecipeInstructions: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("number") number: Int = 15,
    ): RecipeListDto

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val API_KEY = "4bbb550c165d4764ba0807ae1a2197ae"
    }
}