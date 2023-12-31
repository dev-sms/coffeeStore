package com.example.coffeeStore.order.domain

enum class PaymentType(
    val value: String
) {
    CASH("현금"),
    CREDIT_CARD("신용카드")
}