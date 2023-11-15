package com.example.coffeeStore.user.repository

import com.example.coffeeStore.user.domain.Admin
import com.example.coffeeStore.user.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority, Long> {
    fun findAuthoritiesByAdmin(admin: Admin): Set<Authority>
}