package com.example.recipeapp.work_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(0)
    val dataState = _state.asStateFlow()

    private var preferencesJob: Job? = null
    private var workManagerJob: Job? = null

    init {
        startMyWorker()
        observePreferences()
    }

    private fun startMyWorker() {
        val periodicWorkRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(1, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManagerJob = viewModelScope.launch {
            workManager.enqueue(periodicWorkRequest)
        }
    }

    private fun observePreferences() {
        preferencesJob = viewModelScope.launch {
            preferencesManager.newRecipeCountFlow.collect { count ->
                _state.value = count
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        preferencesJob?.cancel()
        workManagerJob?.cancel()
    }
}