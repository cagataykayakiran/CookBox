package com.example.recipeapp.data.remote.response

import com.example.recipeapp.domain.model.ExtendedIngredient

data class ExtendedIngredientDto(
    val id: Int?,
    val aisle: String?,
    val image: String?,
    val consistency: String?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val amount: Double?,
    val unit: String?,
    val meta: List<String>?,
    val measuresDto: MeasuresDto?
)

fun ExtendedIngredientDto.toExtendedIngredient(): ExtendedIngredient {
    return ExtendedIngredient(
        nameClean = nameClean.orEmpty(),
        amount = amount ?: 0.0,
        image = image.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        original = original.orEmpty()
    )
}