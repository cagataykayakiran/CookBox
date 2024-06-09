package com.example.recipeapp.data.repository


import android.util.Log
import com.example.recipeapp.data.local.RecipeDao
import com.example.recipeapp.data.local.RecipeEntity
import com.example.recipeapp.data.local.RecipeWithIngredients
import com.example.recipeapp.data.local.toExtendedIngredient
import com.example.recipeapp.data.local.toRecipeDetail
import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.response.toRecipeDetail
import com.example.recipeapp.data.remote.response.toRecipeList
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.model.toIngredientEntity
import com.example.recipeapp.domain.model.toRecipeEntity
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.NetworkHelper
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao,
    private val networkHelper: NetworkHelper
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipeApi.getBreakfastRecipeList().toRecipeList()
    }

    override suspend fun getTypeByRecipes(diet: String): List<Recipe> {
        return recipeApi.getDietTypeRecipeList(diet).toRecipeList()
    }

    override suspend fun getRecipesByLowCalories(): List<Recipe> {
        return recipeApi.getRecipesByLowCalories().toRecipeList()
    }

    override suspend fun getRecipesByLowReadyTime(): List<Recipe> {
        return recipeApi.getRecipesByLowReadyTime().toRecipeList()
    }

    override suspend fun getRecipesByHighProtein(): List<Recipe> {
        return recipeApi.getRecipesByHighProtein().toRecipeList()
    }

    override suspend fun getRecipesById(id: Int): RecipeDetail {
        return if (networkHelper.isNetworkConnected()) {
            try {
                recipeApi.getRecipesById(id).toRecipeDetail()
            } catch (e: Exception) {
                Log.e("RecipeRepositoryImpl", "getRecipesById: $id ${e.message}")
                val recipeDetail = recipeDao.getRecipeWithIngredientsById(id)
                recipeDetail.recipe.toRecipeDetail(
                    recipeDetail.ingredients.map { it.toExtendedIngredient() }
                )
            }
        } else {
            val recipeDetail = recipeDao.getRecipeWithIngredientsById(id)
            recipeDetail.recipe.toRecipeDetail(
                recipeDetail.ingredients.map { it.toExtendedIngredient() }
            )
        }
    }


    override suspend fun getLocalRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getLocalRecipes()
    }

    override suspend fun saveRecipeWithIngredients(
        recipe: RecipeDetail,
        ingredients: List<ExtendedIngredient>
    ) {
        recipeDao.insertRecipe(recipe.toRecipeEntity(isFavorite = true))
        ingredients.forEach { ingredient ->
            recipeDao.insertIngredientEntity(
                ingredient.toIngredientEntity(recipe.id).copy(recipeId = recipe.id)
            )
        }
    }

    override suspend fun getRecipeWithIngredientsById(recipeId: Int): RecipeWithIngredients {
        return recipeDao.getRecipeWithIngredientsById(recipeId)
    }

}