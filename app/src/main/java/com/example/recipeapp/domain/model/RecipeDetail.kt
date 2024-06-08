package com.example.recipeapp.domain.model

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
)




