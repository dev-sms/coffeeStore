package com.example.coffeeStore.menu.dto

import com.example.coffeeStore.menu.domain.BestMenu
import com.example.coffeeStore.menu.domain.ProductType
import java.time.LocalDateTime

data class BestMenuForm (
    val menuRank: Int,
    val name: String,
    val quantity: Int,
){
    fun toEntity(): BestMenu = BestMenu(
        menuRank = this.menuRank,
        name = this.name,
        quantity = this.quantity
    )
}