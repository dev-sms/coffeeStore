package com.example.coffeeStore.user.repository

import com.example.coffeeStore.user.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.yaml.snakeyaml.events.Event.ID

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findCustomerByPhone(phone: String): Customer?
    fun existsCustomerByPhone(phone: String): Boolean
    fun findCustomerById(id: Long): Customer?
}