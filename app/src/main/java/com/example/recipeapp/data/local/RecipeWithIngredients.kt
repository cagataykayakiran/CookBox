package com.example.recipeapp.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "mainId",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>
)