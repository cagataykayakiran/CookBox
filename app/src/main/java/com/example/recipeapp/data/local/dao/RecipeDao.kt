package com.example.recipeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Query("select * from main_recipes")
    fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: RecipeEntity)
}