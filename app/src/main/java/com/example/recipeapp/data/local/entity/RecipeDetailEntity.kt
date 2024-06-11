package com.example.recipeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeDetail

@Entity(tableName = "recipes_detail")
data class RecipeDetailEntity(
    @PrimaryKey
    val mainId: Int,
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
    val instructions: String,
    val dishTypes: String,
    val isFavorite: Boolean = false,
)

fun RecipeDetailEntity.toRecipe(): Recipe {
    return Recipe(
        id = mainId,
        title = title,
        image = image,
    )
}

fun RecipeDetailEntity.toRecipeDetail(ingredients: List<ExtendedIngredient>): RecipeDetail {
    return RecipeDetail(
        id = mainId,
        title = title,
        image = image,
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
        extendedIngredients = ingredients,
        instructions = instructions,
        dishTypes = dishTypes.split(","),
        isFavorite = isFavorite
    )
}

