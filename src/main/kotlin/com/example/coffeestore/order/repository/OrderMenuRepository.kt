package com.example.coffeeStore.order.repository

import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.order.domain.OrderMenu
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository : JpaRepository<OrderMenu, Long> {
    fun findOrderMenuById(id: Long): OrderMenu?
}