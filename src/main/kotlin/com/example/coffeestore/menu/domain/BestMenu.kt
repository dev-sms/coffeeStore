package com.example.coffeeStore.menu.domain

import com.example.coffeeStore.global.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class BestMenu(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val quantity: Number
) : BaseEntity(){
    
}