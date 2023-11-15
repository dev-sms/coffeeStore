package com.example.coffeeStore.global.domain

import jakarta.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}