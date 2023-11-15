package com.example.coffeeStore.user.repository

import com.example.coffeeStore.user.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findCustomerByPhone(phone: String): Customer?
    fun existsCustomerByPhone(phone: String): Boolean
}