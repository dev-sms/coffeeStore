package com.example.coffeeStore.vendor.service

import com.example.coffeeStore.vendor.domain.Vendor
import com.example.coffeeStore.vendor.dto.VendorForm
import com.example.coffeeStore.vendor.repository.VendorRepository
import org.springframework.stereotype.Service

@Service
class VendorService(
    private val vendorRepository: VendorRepository
) {
    fun getVendorList(): List<Vendor> {
        return vendorRepository.findAll()
    }

    fun saveNewVendor(vendorForm: VendorForm) {
        vendorRepository.save(Vendor(name = vendorForm.name, address = vendorForm.address))
    }

    fun getVendor(vendorId: Long): Vendor =
        vendorRepository.findVendorById(vendorId)
            ?: throw NoSuchElementException("해당 공급업체를 찾을 수 없습니다 : $vendorId")
}