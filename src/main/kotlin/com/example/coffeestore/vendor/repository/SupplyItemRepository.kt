package com.example.coffeeStore.vendor.repository

import com.example.coffeeStore.vendor.domain.SupplyItem
import org.springframework.data.jpa.repository.JpaRepository

interface SupplyItemRepository : JpaRepository<SupplyItem, Long>