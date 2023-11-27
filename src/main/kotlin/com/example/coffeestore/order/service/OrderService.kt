package com.example.coffeeStore.order.service

import com.example.coffeeStore.menu.service.MenuService
import com.example.coffeeStore.menu.service.RecipeService
import com.example.coffeeStore.order.domain.CustomerOrder
import com.example.coffeeStore.order.domain.PaymentType
import com.example.coffeeStore.order.dto.*
import com.example.coffeeStore.order.repository.CustomOrderRepository
import com.example.coffeeStore.order.repository.OrderMenuRepository
import com.example.coffeeStore.user.repository.CustomerRepository
import com.example.coffeeStore.user.service.CustomerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val customOrderRepository: CustomOrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val customerService: CustomerService,
    private val menuService: MenuService,
    private val recipeService: RecipeService,
    private val customerRepository: CustomerRepository
) {
    @Transactional
    fun processOrder(customerPhone: String, paymentType: PaymentType, cart: Cart) {
        val customer = customerService.getCustomerByPhone(customerPhone)
        val order = customOrderRepository.save(
            CustomerOrder(
                customer = customer,
                paymentType = paymentType,
                totalPrice = 0,
                saledPrice = 0
            )
        )
        cart.items.forEach { cartItem ->
            var saleNum: Int = 0
            if(customer.customerGrade == "Bronze")
                saleNum = 5
            else if(customer.customerGrade == "Silver")
                saleNum = 10
            else if(customer.customerGrade == "Gold")
                saleNum = 20
            customer.updateTotalPrice(cartItem.toEntity(menuService.getMenuById(cartItem.itemId), order, saleNum).price)
            order.updateTotalPrice(cartItem.toEntity(menuService.getMenuById(cartItem.itemId), order, 0).price)
            order.updateSaledPrice(cartItem.toEntity(menuService.getMenuById(cartItem.itemId), order, 100-saleNum).price)
            val orderMenu = orderMenuRepository.save(cartItem.toEntity(menuService.getMenuById(cartItem.itemId), order, saleNum))
            recipeService.updateRecipesOnOrder(orderMenu)
        }
        customer.updateCustomerGrade()

    }

    @Transactional(readOnly = true)
    fun getOrderHistories(): List<OrderInfo> =
        customOrderRepository.findAll().map { it.toOrderInfo() }

    @Transactional(readOnly = true)
    fun getOrderHistory(orderId: Long): OrderInfo =
        customOrderRepository.findCustomerOrderById(orderId)?.toOrderInfo()
            ?: throw NoSuchElementException("해당 주문을 찾을 수 없습니다 : $orderId")

    fun convertCartToDtoList(cart: Cart): List<CartItemDisplay> =
        cart.items.map { it.toCartItemDisplay(menuService.getMenuById(it.itemId)) }

}