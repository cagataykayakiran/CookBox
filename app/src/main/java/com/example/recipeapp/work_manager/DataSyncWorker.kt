package com.example.recipeapp.work_manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DataSyncWorker  @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val dataSyncRepository: DataSyncRepository,
) : CoroutineWorker(
    context, workerParameters
) {

    override suspend fun doWork(): Result {
        return try {
            val newRecipes = dataSyncRepository.syncData()
            val sharedPreferences =
                applicationContext.getSharedPreferences("worker", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("new_recipe_count", newRecipes).apply()
            println("DataSyncWorker $newRecipes")
            Result.success()
        } catch (e: Exception) {
             Result.retry()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}