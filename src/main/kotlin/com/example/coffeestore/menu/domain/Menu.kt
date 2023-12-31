package com.example.coffeeStore.menu.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import com.example.coffeeStore.menu.dto.MenuDisplay
import jakarta.persistence.*

@Entity
class Menu(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Int,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val productType: ProductType,
    @Column(nullable = false)
    var specialMenu: Boolean,
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    var specialMenuType: SpecialMenuType?
) : BaseTimeEntity() {
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    private val _recipes: MutableList<Recipe> = mutableListOf()
    val recipes: List<Recipe>
        get() = _recipes

    fun isSoldOuted(): Boolean = this.recipes.any { (it.ingredient.stock - it.amount) < 0 }

    fun toDisplay(): MenuDisplay =
        MenuDisplay(
            id = this.id,
            name = this.name,
            price = this.price,
            productType = this.productType.title,
            soldOuted = this.isSoldOuted(),
            recipes = this.recipes.map { it.toRecipeInfo() },
            specialMenu = this.specialMenu,
            specialMenuType = this.specialMenuType?.title
        )
}