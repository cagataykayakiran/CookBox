package com.example.recipeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp.domain.model.Recipe

@Entity(tableName = "main_recipes")
data class RecipeEntity (
    @PrimaryKey
    val mainId: Int,
    val title: String,
    val image: String,
)

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = mainId,
        title = title,
        image = image,
    )
}

