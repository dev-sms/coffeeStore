package com.example.coffeestore.menu.domain

import com.example.coffeestore.global.domain.BaseEntity
import com.example.coffeestore.menu.dto.RecipeInfo
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Recipe(
    @ManyToOne(fetch = FetchType.LAZY)
    val menu: Menu,
    @ManyToOne(fetch = FetchType.LAZY)
    val ingredient: Ingredient,
    @Column(nullable = false)
    var amount: Double
) : BaseEntity() {
    fun toRecipeInfo() : RecipeInfo = RecipeInfo(
        id = this.id,
        ingredient = this.ingredient.name,
        amount = this.amount,
        measurementUnit = this.ingredient.measurementUnit.initial
    )
}