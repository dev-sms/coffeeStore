package com.example.coffeeStore.menu.dto

import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.ProductType
import com.example.coffeeStore.menu.domain.SpecialMenuType

data class MenuForm(
    val name: String,
    val price: Int,
    val productType: ProductType,
    val specialMenu: Boolean = false,
    val specialMenuType: SpecialMenuType?,
    val recipes: List<RecipeForm> = mutableListOf()
) {
    fun toEntity(): Menu =
        Menu(
            name = this.name,
            productType = this.productType,
            price = this.price,
            specialMenu = this.specialMenu,
            specialMenuType = this.specialMenuType
        )
}
