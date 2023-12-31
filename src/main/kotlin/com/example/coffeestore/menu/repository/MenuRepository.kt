package com.example.coffeeStore.menu.repository

import com.example.coffeeStore.menu.domain.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findMenuById(id: Long): Menu?
    fun findMenusByNameContains(name: String): List<Menu>
}