package com.example.coffeeStore.order.repository

import com.example.coffeeStore.order.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>{
    fun findOrderById(Id: Long): Order?
}