package com.example.coffeestore.menu.dto

data class MenuDisplay(
    val id: Long,
    val name: String,
    val price: Int,
    val productType: String,
    val specialMenu: Boolean,
    val soldOuted: Boolean,
    val recipes: List<RecipeInfo> = mutableListOf()
)
