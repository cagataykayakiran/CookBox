package com.example.recipeapp.data.repository


import com.example.recipeapp.data.local.entity.RecipeEntity
import com.example.recipeapp.data.local.dao.RecipeDao
import com.example.recipeapp.data.local.entity.RecipeDetailEntity
import com.example.recipeapp.data.local.RecipeWithIngredients
import com.example.recipeapp.data.local.dao.IngredientDao
import com.example.recipeapp.data.local.dao.RecipeDetailDao
import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.response.toRecipeDetail
import com.example.recipeapp.data.remote.response.toRecipeList
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.model.toIngredientEntity
import com.example.recipeapp.domain.model.toRecipeEntity
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao,
    private val recipeDetailDao: RecipeDetailDao,
    private val ingredientDao: IngredientDao
) : RecipeRepository {

    override suspend fun getPopularRecipes(): List<Recipe> {
        return recipeApi.getPopularRecipes().toRecipeList()
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
        return recipeApi.getRecipesById(id).toRecipeDetail()
    }

    override suspend fun getLocalRecipeDetail(): Flow<List<RecipeDetailEntity>> {
        return recipeDetailDao.getFavoriteRecipe()
    }

    override suspend fun insertRecipeDetailWithIngredients(
        recipe: RecipeDetail,
        ingredients: List<ExtendedIngredient>,
        isFavorite: Boolean
    ) {
        recipeDetailDao.insertRecipeDetail(recipe.toRecipeEntity(isFavorite = isFavorite))
        ingredients.forEach { ingredient ->
            ingredientDao.insertIngredientEntity(
                ingredient.toIngredientEntity(recipe.id).copy(recipeId = recipe.id)
            )
        }
    }

    override suspend fun getRecipeWithIngredientsById(recipeId: Int): RecipeWithIngredients {
        return recipeDetailDao.getRecipeWithIngredientsById(recipeId)
    }

    override suspend fun getLocalRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getRecipes()
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        return recipeDao.insertRecipe(recipe)
    }
}