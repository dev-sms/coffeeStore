package com.example.coffeeStore.user.service

import com.example.coffeeStore.user.domain.Customer
import com.example.coffeeStore.user.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun isExistsPhone(phone: String): Boolean =
        customerRepository.existsCustomerByPhone(phone)

    fun addCustomer(customer: Customer) {
        customerRepository.save(customer)
    }

    fun getCustomerByPhone(customerPhone: String): Customer = customerRepository.findCustomerByPhone(customerPhone)
        ?: throw NoSuchElementException("해당 고객을 찾을 수 없습니다 : $customerPhone")
}