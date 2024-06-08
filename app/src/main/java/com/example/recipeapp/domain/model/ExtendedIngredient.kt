package com.example.recipeapp.domain.model

data class ExtendedIngredient(
    val id: Int,
    val nameClean: String,
    val amount: Double,
    val image: String,
    val name: String,
    val original: String
)
