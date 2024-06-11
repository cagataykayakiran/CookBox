package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.data.local.entity.toExtendedIngredient
import com.example.recipeapp.data.local.entity.toRecipeDetail
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.NetworkHelper
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeById @Inject constructor(
    private val repository: RecipeRepository,
    private val networkHelper: NetworkHelper
) {
    operator fun invoke(id: Int): Flow<Resource<RecipeDetail>> = flow {
        emit(Resource.Loading())
        if (networkHelper.isNetworkConnected()) {
            try {
                val recipe = repository.getRecipesById(id)
                emit(Resource.Success(recipe))
            } catch (e: Exception) {
                val recipeDetail = repository.getRecipeWithIngredientsById(id)
                if (recipeDetail != null) {
                    emit(Resource.Success(
                        recipeDetail.recipe.toRecipeDetail(
                            recipeDetail.ingredients.map { it.toExtendedIngredient() }
                        )
                    ))
                } else {
                    emit(Resource.Error("Unexpected error!"))
                }
            }
        } else {
            val recipeDetail = repository.getRecipeWithIngredientsById(id)
            if (recipeDetail != null) {
                emit(Resource.Success(
                    recipeDetail.recipe.toRecipeDetail(
                        recipeDetail.ingredients.map { it.toExtendedIngredient() }
                    )
                ))
            } else {
                emit(Resource.Error("Unexpected error!"))
            }
        }
    }
}
