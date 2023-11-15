package com.example.coffeeStore.vendor.repository

import com.example.coffeeStore.vendor.domain.Supply
import org.springframework.data.jpa.repository.JpaRepository

interface SupplyRepository : JpaRepository<Supply, Long> {
    fun findSupplyById(id: Long): Supply?
}