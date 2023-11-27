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
    val address: String,
    @Column(nullable = false)
    var totalPrice: Int = 0,
    @Column(nullable = true)
    var customerGrade: String?
) : BaseTimeEntity() {
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private val _customerOrders: MutableList<CustomerOrder> = mutableListOf()
    val customerOrders: List<CustomerOrder>
        get() = _customerOrders

    fun updateTotalPrice(totalPrice: Int){
        this.totalPrice += totalPrice
        //updateCustomerGrade()
    }
    fun updateCustomerGrade(){
        if(this.totalPrice >= 50000){
            this.customerGrade = "Gold"
        }
        else if(this.totalPrice >= 30000){
            this.customerGrade = "Silver"
        }
        else if(this.totalPrice >= 10000){
            this.customerGrade = "Bronze"
        }
    }
}