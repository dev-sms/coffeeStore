package com.example.coffeeStore.vendor.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

@Entity
class Vendor(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val address: String
) : BaseTimeEntity() {
    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private val _supplies: MutableList<Supply> = mutableListOf()
    val supply: List<Supply>
        get() = _supplies
}