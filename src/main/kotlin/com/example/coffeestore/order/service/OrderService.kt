package com.example.coffeeStore.order.service

import com.example.coffeeStore.menu.service.MenuService
import com.example.coffeeStore.menu.service.RecipeService
import com.example.coffeeStore.order.domain.CustomerOrder
import com.example.coffeeStore.order.domain.PaymentType
import com.example.coffeeStore.order.dto.*
import com.example.coffeeStore.order.repository.CustomOrderRepository
import com.example.coffeeStore.order.repository.OrderMenuRepository
import com.example.coffeeStore.user.service.CustomerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val customOrderRepository: CustomOrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val customerService: CustomerService,
    private val menuService: MenuService,
    private val recipeService: RecipeService
) {
    @Transactional
    fun processOrder(customerPhone: String, paymentType: PaymentType, cart: Cart) {
        val customer = customerService.getCustomerByPhone(customerPhone)
        val order = customOrderRepository.save(
            CustomerOrder(
                customer = customer,
                paymentType = paymentType
            )
        )
        cart.items.forEach { cartItem ->
            val orderMenu = orderMenuRepository.save(cartItem.toEntity(menuService.getMenuById(cartItem.itemId), order))
            recipeService.updateRecipesOnOrder(orderMenu)
        }
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