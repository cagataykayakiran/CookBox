package com.example.recipeapp.data.remote.response

import com.example.recipeapp.domain.model.Recipe

data class RecipeDto(
    val id: Int?,
    val title: String?,
    val image: String?,
    val imageType: String?
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(id = id ?: 0, title = title ?: "", image = image ?: "")
}