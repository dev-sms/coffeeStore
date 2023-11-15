package com.example.coffeeStore.order.dto

data class CartItemDisplay(
    val itemId: Long,
    val menu: String,
    val price: Int,
    val quantity: Int,
) {
    val totalPrice: Int
        get() = price * quantity
}
