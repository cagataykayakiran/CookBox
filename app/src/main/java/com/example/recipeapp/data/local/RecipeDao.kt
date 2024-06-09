package com.example.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("select * from recipes where isFavorite = 1")
    fun getLocalRecipes(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientEntity(ingredientEntity: IngredientEntity)

    @Transaction
    @Query("SELECT * FROM recipes WHERE mainId = :recipeId")
    suspend fun getRecipeWithIngredientsById(recipeId: Int): RecipeWithIngredients
}