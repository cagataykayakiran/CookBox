package com.example.recipeapp.work_manager

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("worker", Context.MODE_PRIVATE)

    private val _newRecipeCountFlow = MutableStateFlow(getNewRecipeCount())
    val newRecipeCountFlow: Flow<Int> = _newRecipeCountFlow.asStateFlow()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            if (key == "new_recipe_count") {
                _newRecipeCountFlow.value = getNewRecipeCount()
            }
        }
    }

    private fun getNewRecipeCount(): Int {
        return sharedPreferences.getString("new_recipe_count", "0")?.toInt() ?: 0
    }
}