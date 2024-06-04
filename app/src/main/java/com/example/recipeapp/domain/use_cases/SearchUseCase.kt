package com.example.recipeapp.domain.use_cases

import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl
) {
    operator fun invoke(query: String): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val recipes = searchRepositoryImpl.getRecipeByName(query)
            emit(Resource.Success(recipes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}