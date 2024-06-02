package com.example.recipeapp.data.remote.response

import com.example.recipeapp.domain.model.Recipe
import com.google.gson.annotations.SerializedName

data class RecipeListDto(
    @SerializedName("results")
    val recipeDto: List<RecipeDto?>,
    val offset: Int?,
    val number: Int?,
    val totalResults: Int?
)

fun RecipeListDto.toRecipeList(): List<Recipe> {
    return recipeDto.mapNotNull { it?.toRecipe() }
}