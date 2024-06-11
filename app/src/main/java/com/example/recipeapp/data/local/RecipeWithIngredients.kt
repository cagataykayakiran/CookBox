package com.example.recipeapp.data.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.recipeapp.data.local.entity.IngredientEntity
import com.example.recipeapp.data.local.entity.RecipeDetailEntity

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeDetailEntity,
    @Relation(
        parentColumn = "mainId",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>
)