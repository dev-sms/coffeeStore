package com.example.coffeeStore.order.repository

import com.example.coffeeStore.order.domain.CustomerOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CustomOrderRepository : JpaRepository<CustomerOrder, Long> {
    fun findCustomerOrderById(orderId: Long): CustomerOrder?
}