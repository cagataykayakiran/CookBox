package com.example.recipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.data.local.dao.IngredientDao
import com.example.recipeapp.data.local.dao.RecipeDao
import com.example.recipeapp.data.local.dao.RecipeDetailDao
import com.example.recipeapp.data.local.entity.IngredientEntity
import com.example.recipeapp.data.local.entity.RecipeEntity
import com.example.recipeapp.data.local.entity.RecipeDetailEntity

@Database(
    entities = [
        RecipeDetailEntity::class, IngredientEntity::class, RecipeEntity::class],
    exportSchema = false,
    version = 7
)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailDao(): RecipeDetailDao
    abstract fun ingredientDao(): IngredientDao
}