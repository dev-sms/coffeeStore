package com.example.coffeeStore.order.dto

import com.example.coffeeStore.order.domain.PaymentType

data class OrderForm(
    val customerPhone: String,
    val paymentType: PaymentType
)
