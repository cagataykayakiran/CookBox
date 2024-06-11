package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.data.local.entity.toExtendedIngredient
import com.example.recipeapp.data.local.entity.toRecipeDetail
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeById @Inject constructor(
    private val repository: RecipeRepository,
) {
    operator fun invoke(id: Int): Flow<Resource<RecipeDetail>> = flow {
        emit(Resource.Loading())

        val recipeDetail = repository.getRecipeWithIngredientsById(id)
        if (recipeDetail == null) try {
            val recipe = repository.getRecipesById(id)
            emit(Resource.Success(recipe))
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected error!"))
        } else emit(Resource.Success(
            recipeDetail.recipe.toRecipeDetail(
                recipeDetail.ingredients.map { it.toExtendedIngredient() }
            )
        ))
    }
}
