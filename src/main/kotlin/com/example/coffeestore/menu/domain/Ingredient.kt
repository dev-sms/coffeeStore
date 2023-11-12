package com.example.coffeestore.menu.domain

import com.dnlab.coffee.menu.domain.MeasurementUnit
import com.example.coffeestore.global.domain.BaseEntity
import java.time.temporal.TemporalAmount
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Ingredient (
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