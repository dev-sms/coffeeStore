package com.example.coffeeStore.user.domain

import com.example.coffeeStore.global.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany


@Entity
class Admin(
    @Column(nullable = false, unique = true)
    val username: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val enabled: Boolean = true
) : BaseTimeEntity() {

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private val _authorities: MutableSet<Authority> = mutableSetOf()
    val authorities: Set<Authority>
        get() = _authorities
}