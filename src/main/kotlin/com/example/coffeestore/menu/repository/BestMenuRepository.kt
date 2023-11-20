package com.example.coffeeStore.menu.repository

import com.example.coffeeStore.menu.domain.BestMenu
import org.springframework.data.jpa.repository.JpaRepository

interface BestMenuRepository : JpaRepository<BestMenu, Long>{
}