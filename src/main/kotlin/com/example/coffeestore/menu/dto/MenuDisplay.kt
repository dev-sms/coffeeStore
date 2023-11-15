package com.example.coffeeStore.menu.dto

data class MenuDisplay(
    val id: Long,
    val name: String,
    val price: Int,
    val productType: String,
    val soldOuted: Boolean,
    val recipes: List<RecipeInfo> = mutableListOf(),
    val specialMenu: Boolean,
    val specialMenuType: String?
)
