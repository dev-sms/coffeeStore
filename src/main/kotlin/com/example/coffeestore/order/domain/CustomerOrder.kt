package com.example.coffeeStore.order.domain

import com.example.coffeeStore.global.domain.BaseEntity
import com.example.coffeeStore.order.dto.OrderInfo
import com.example.coffeeStore.user.domain.Customer
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class CustomerOrder(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    val customer: Customer,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val paymentType: PaymentType
) : BaseEntity() {
    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY)
    private val _orderMenus: MutableList<OrderMenu> = mutableListOf()
    val orderMenus: List<OrderMenu>
        get() = _orderMenus

    @CreatedDate
    @Column(nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    fun toOrderInfo(): OrderInfo =
        OrderInfo(
            id = this.id,
            customer = this.customer.name,
            customerPhone = this.customer.phone,
            paymentType = this.paymentType.value,
            createdAt = this.createdAt,
            menus = this.orderMenus.map { it.toOrderMenuInfo() }
        )
}