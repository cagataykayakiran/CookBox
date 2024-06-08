package com.example.recipeapp.data.remote.response

import com.example.recipeapp.domain.model.RecipeDetail

data class RecipeDetailDto(
    val id: Int?,
    val title: String?,
    val image: String?,
    val vegetarian: Boolean?,
    val vegan: Boolean?,
    val glutenFree: Boolean?,
    val dairyFree: Boolean?,
    val veryHealthy: Boolean?,
    val cheap: Boolean?,
    val veryPopular: Boolean?,
    val sustainable: Boolean?,
    val weightWatcherSmartPoints: Int?,
    val aggregateLikes: Int?,
    val healthScore: Int?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceUrl: String?,
    val summary: String?,
    val diets: List<String>?,
    val extendedIngredients: List<ExtendedIngredientDto>?,
    val instructions: String?,
    val dishTypes: List<String>?,
    val analyzedInstructions: List<AnalyzedInstructionDto>?
)

fun RecipeDetailDto.toRecipeDetail(): RecipeDetail {
    return RecipeDetail(
        id = id ?: 0,
        vegetarian = vegetarian ?: false ,
        vegan = vegan ?: false,
        glutenFree = glutenFree ?: false,
        dairyFree = dairyFree ?: false,
        veryHealthy = veryHealthy ?: false,
        cheap = cheap ?: false,
        veryPopular = veryPopular ?: false,
        sustainable = sustainable ?: false,
        weightWatcherSmartPoints = weightWatcherSmartPoints ?: 0,
        aggregateLikes = aggregateLikes ?: 0,
        healthScore = healthScore ?: 0,
        pricePerServing = pricePerServing ?: 0.0,
        extendedIngredients = extendedIngredients.let { it ->
            if (it.isNullOrEmpty()) emptyList() else it.map { it.toExtendedIngredient() }
        },
        title = title.orEmpty(),
        readyInMinutes = readyInMinutes ?: 0,
        servings = servings ?: 0,
        sourceUrl = sourceUrl.orEmpty(),
        image = image.orEmpty(),
        summary = summary.orEmpty(),
        instructions = instructions.orEmpty(),
        dishTypes = dishTypes ?: emptyList()
    )
}



