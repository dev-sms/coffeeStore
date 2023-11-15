package com.example.coffeeStore.menu.domain

import com.example.coffeeStore.global.domain.BaseEntity
import com.example.coffeeStore.menu.dto.RecipeInfo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class Recipe(
    @ManyToOne(fetch = FetchType.LAZY)
    val menu: Menu,
    @ManyToOne(fetch = FetchType.LAZY)
    val ingredient: Ingredient,
    @Column(nullable = false)
    var amount: Double
) : BaseEntity() {
    fun toRecipeInfo(): RecipeInfo =
        RecipeInfo(
            id = this.id,
            ingredient = this.ingredient.name,
            amount = this.amount,
            measurementUnit = this.ingredient.measurementUnit.initial
        )
}