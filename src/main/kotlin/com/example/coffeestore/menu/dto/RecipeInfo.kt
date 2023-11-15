package com.example.coffeeStore.menu.dto

data class RecipeInfo(
    val id: Long,
    val ingredient: String,
    val amount: Double,
    val measurementUnit: String
)
