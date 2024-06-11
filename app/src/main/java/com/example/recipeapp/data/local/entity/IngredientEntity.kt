package com.example.recipeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp.domain.model.ExtendedIngredient

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey
    val id: Int,
    val recipeId: Int,
    val nameClean: String,
    val amount: Double,
    val image: String,
    val name: String,
    val original: String
)

fun IngredientEntity.toExtendedIngredient(): ExtendedIngredient {
    return ExtendedIngredient(
        id = id,
        nameClean = nameClean,
        amount = amount,
        image = image,
        name = name,
        original = original
    )
}
