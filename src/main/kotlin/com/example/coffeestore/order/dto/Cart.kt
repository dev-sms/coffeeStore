package com.example.coffeeStore.order.dto

data class Cart(
    val items: MutableList<CartItem> = mutableListOf()
)
