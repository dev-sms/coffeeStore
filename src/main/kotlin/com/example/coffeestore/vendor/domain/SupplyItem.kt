package com.example.coffeeStore.vendor.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.vendor.dto.SupplyItemInfo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class SupplyItem(
    @ManyToOne(fetch = FetchType.LAZY)
    val ingredient: Ingredient,
    @ManyToOne(fetch = FetchType.LAZY)
    val supply: Supply,
    @Column(nullable = false)
    val amount: Double,
    @Column(nullable = false)
    val price: Int
) : BaseTimeEntity() {
    fun toSupplyItemInfo(): SupplyItemInfo =
        SupplyItemInfo(
            ingredient = this.ingredient.name,
            measurementUnit = this.ingredient.measurementUnit.initial,
            amount = this.amount,
            price = this.price
        )
}