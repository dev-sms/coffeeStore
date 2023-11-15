package com.example.coffeeStore.user.repository

import com.example.coffeeStore.user.domain.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long> {
    fun existsAdminByUsername(username: String): Boolean
    fun findAdminByUsername(username: String): Admin
}