package com.example.coffeeStore.menu.repository

import com.example.coffeeStore.menu.domain.BestMenu
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface BestMenuRepository : JpaRepository<BestMenu, Long>{
    fun findAllByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<BestMenu>
    fun deleteByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime)
}