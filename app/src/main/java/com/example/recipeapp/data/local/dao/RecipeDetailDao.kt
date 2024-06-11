package com.example.recipeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.recipeapp.data.local.RecipeWithIngredients
import com.example.recipeapp.data.local.entity.RecipeDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipeDetail(recipe: RecipeDetailEntity)

    @Query("select * from recipes_detail where isFavorite = 1")
    fun getFavoriteRecipe(): Flow<List<RecipeDetailEntity>>

    @Transaction
    @Query("SELECT * FROM recipes_detail WHERE mainId = :recipeId")
    suspend fun getRecipeWithIngredientsById(recipeId: Int): RecipeWithIngredients
}