package com.example.coffeeStore.order.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import com.example.coffeeStore.menu.domain.Menu
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class Order(
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    val menu: Menu,
    @Column(nullable = false)
    var totalPrice: Int,
    @Column(nullable = false)
    var saledPrice: Int
    ): BaseTimeEntity(){

}
