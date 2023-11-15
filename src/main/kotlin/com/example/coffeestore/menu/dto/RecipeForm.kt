package com.example.coffeeStore.menu.dto

import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.Recipe

data class RecipeForm(
    var ingredientId: Long = 0,
    var amount: Double = 0.0
) {
    fun toEntity(menu: Menu, ingredient: Ingredient): Recipe =
        Recipe(
            amount = this.amount,
            menu = menu,
            ingredient = ingredient
        )
}
