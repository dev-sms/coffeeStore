package com.example.coffeeStore.menu.dto

data class NewRecipeForm(
    val recipes: List<RecipeForm> = mutableListOf()
)
