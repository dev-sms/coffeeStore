package com.example.coffeeStore.user.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import com.example.coffeeStore.order.domain.CustomerOrder
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

@Entity
class Customer(
    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val phone: String,

    @Column(nullable = false)
    val address: String
) : BaseTimeEntity() {
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private val _customerOrders: MutableList<CustomerOrder> = mutableListOf()
    val customerOrders: List<CustomerOrder>
        get() = _customerOrders
}