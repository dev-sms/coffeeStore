package com.example.coffeeStore.vendor.dto

import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.vendor.domain.Supply
import com.example.coffeeStore.vendor.domain.SupplyItem

data class SupplyItemForm(
    var ingredientId: Long = 0,
    var amount: Double = 0.0,
    var price: Int = 0
) {
    fun toEntity(supply: Supply, ingredient: Ingredient) =
        SupplyItem(
            supply = supply,
            ingredient = ingredient,
            amount = this.amount,
            price = this.price
        )
}
