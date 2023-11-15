package com.example.coffeeStore.vendor.dto

import com.example.coffeeStore.vendor.domain.Supply
import com.example.coffeeStore.vendor.domain.Vendor
import java.time.LocalDate

data class SupplyForm(
    val deliveryDate: LocalDate,
    val actualDeliveryDate: LocalDate?,
    val vendorId: Long,
    val supplyItems: List<SupplyItemForm> = mutableListOf()
) {
    fun toEntity(vendor: Vendor): Supply =
        Supply(
            deliveryDate = this.deliveryDate,
            actualDeliveryDate = this.actualDeliveryDate,
            vendor = vendor
        )
}
