package com.example.recipeapp.data.remote.response

data class AnalyzedInstructionDto(
    val name: String?,
    val stepDtos: List<StepDto>?
)