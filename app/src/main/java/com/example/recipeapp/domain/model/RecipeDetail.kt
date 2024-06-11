package com.example.recipeapp.domain.model

import com.example.recipeapp.data.local.entity.RecipeDetailEntity

data class RecipeDetail(
    val id: Int,
    val title: String,
    val image: String,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val veryHealthy: Boolean,
    val cheap: Boolean,
    val veryPopular: Boolean,
    val sustainable: Boolean,
    val weightWatcherSmartPoints: Int,
    val aggregateLikes: Int,
    val healthScore: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val summary: String,
    val extendedIngredients: List<ExtendedIngredient>,
    val instructions: String,
    val dishTypes: List<String>,
    val isFavorite: Boolean = false
)

fun RecipeDetail.toRecipeEntity(isFavorite: Boolean = false): RecipeDetailEntity {
    return RecipeDetailEntity(
        title = title,
        image =image,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        veryHealthy = veryHealthy,
        cheap = cheap,
        veryPopular = veryPopular,
        sustainable = sustainable,
        weightWatcherSmartPoints = weightWatcherSmartPoints,
        aggregateLikes = aggregateLikes,
        healthScore = healthScore,
        pricePerServing = pricePerServing,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceUrl = sourceUrl,
        summary = summary,
        instructions = instructions,
        dishTypes = dishTypes.joinToString(","),
        mainId = id,
        isFavorite = isFavorite
    )
}




