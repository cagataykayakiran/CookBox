package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesByHighProtein @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val recipes = recipeRepository.getRecipesByHighProtein()
            emit(Resource.Success(recipes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}