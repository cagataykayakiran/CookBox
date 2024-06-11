package com.example.recipeapp.domain.model

import com.example.recipeapp.data.local.entity.IngredientEntity

data class ExtendedIngredient(
    val id: Int,
    val nameClean: String,
    val amount: Double,
    val image: String,
    val name: String,
    val original: String
)

fun ExtendedIngredient.toIngredientEntity(recipeId: Int): IngredientEntity {
    return IngredientEntity(
        id = id,
        recipeId = recipeId,
        nameClean = nameClean,
        amount =amount,
        image = image,
        name = name,
        original = original
    )
}
