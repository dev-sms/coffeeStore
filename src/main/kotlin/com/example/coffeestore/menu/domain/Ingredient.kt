package com.example.coffeeStore.menu.domain

import com.example.coffeeStore.global.domain.BaseEntity
import jakarta.persistence.*

@Entity
class Ingredient(
    @Column(nullable = false, unique = true)
    val name: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val measurementUnit: MeasurementUnit,
    @Column(nullable = false)
    var stock: Double = 0.0
): BaseEntity() {
    fun addStock(amount: Double) {
        this.stock += amount
    }
}