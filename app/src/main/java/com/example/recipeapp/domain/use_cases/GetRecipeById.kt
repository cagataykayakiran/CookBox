package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipeById @Inject constructor(
    private val repository: RecipeRepository,
) {
    operator fun invoke(id: Int): Flow<Resource<RecipeDetail>> = flow {
        try {
            emit(Resource.Loading())
            val recipe = repository.getRecipesById(id)
            emit(Resource.Success(recipe))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}