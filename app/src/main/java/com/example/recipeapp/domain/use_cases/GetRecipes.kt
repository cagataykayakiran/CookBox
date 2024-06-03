package com.example.recipeapp.domain.use_cases

import com.example.myapplication.util.NetworkHelper
import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipes @Inject constructor(
    private val repository: RecipeRepository,
    private val networkHelper: NetworkHelper
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading(isLoading = true))
            val recipes = repository.getRecipes()
            emit(Resource.Success(recipes))
            emit(Resource.Loading(isLoading = false))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}