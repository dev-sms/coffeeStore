package com.example.coffeestore.menu.domain

import com.dnlab.coffee.menu.domain.ProductType
import com.example.coffeestore.global.domain.BaseTimeEntity
import com.example.coffeestore.menu.dto.MenuDisplay
import com.example.coffeestore.menu.dto.RecipeInfo
import javax.persistence.*

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
    var specialMenu: Boolean
) : BaseTimeEntity() {
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    private val _recipes: MutableList<Recipe> = mutableListOf()
    val recipes: List<Recipe>
        get() = _recipes
    //fun isSoldOuted(): Boolean = this.recipes.any { (it.ingredient.stock - it.amount) < 0}
    fun toDisplay(): MenuDisplay = MenuDisplay(
        id = this.id,
        name = this.name,
        price = this.price,
        productType = this.productType.title,
        specialMenu = this.specialMenu,
        soldOuted = false, //soldOuted = this.isSoldOuted(),
        recipes = this.recipes.map { it.toRecipeInfo() }
    )
}