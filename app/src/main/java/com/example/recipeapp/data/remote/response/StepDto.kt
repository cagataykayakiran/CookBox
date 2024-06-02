package com.example.recipeapp.data.remote.response

data class StepDto(
    val number: Int?,
    val step: String?,
    val ingredients: List<IngredientDto?>?,
    val equipmentDtos: List<EquipmentDto?>?,
    val lengthDto: LengthDto?
)