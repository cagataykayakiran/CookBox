package com.example.recipeapp.domain.model

import com.example.recipeapp.data.local.entity.RecipeEntity

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
)

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        mainId = id,
        title = title,
        image = image,
    )
}
