package com.example.recipeapp.work_manager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(0)
    val dataState = _state.asStateFlow()


    init {
        startMyWorker()
        observePreferences()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest =
            PeriodicWorkRequestBuilder<DataSyncWorker>(1, TimeUnit.HOURS)
                .setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.LINEAR,
                    duration = Duration.ofSeconds(15)
                )
                .setConstraints(constraints)
                .build()

        workManager
            .enqueueUniquePeriodicWork(
                "DATA_SYNC_WORKER",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }

    private fun observePreferences() {
        viewModelScope.launch {
            preferencesManager.newRecipeCountFlow.collect { count ->
                _state.value = count
            }
        }
    }
}