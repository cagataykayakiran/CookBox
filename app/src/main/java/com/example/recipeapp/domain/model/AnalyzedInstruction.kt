package com.example.recipeapp.domain.model

import com.example.recipeapp.data.remote.response.StepDto

data class AnalyzedInstruction(
    val stepDtos: List<StepDto>
)
