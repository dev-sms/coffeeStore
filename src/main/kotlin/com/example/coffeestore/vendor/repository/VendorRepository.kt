package com.example.coffeeStore.vendor.repository

import com.example.coffeeStore.vendor.domain.Vendor
import org.springframework.data.jpa.repository.JpaRepository

interface VendorRepository : JpaRepository<Vendor, Long> {
    fun findVendorById(id: Long): Vendor?
}