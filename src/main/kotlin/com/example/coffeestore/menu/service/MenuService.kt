package com.example.coffeestore.menu.service

import com.example.coffeestore.menu.dto.*
import com.example.coffeestore.menu.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuService (
    private val menuRepository: MenuRepository
) {
    @Transactional(readOnly = true)
    fun getMenuDisplays(): List<MenuDisplay> = menuRepository.findAll().map { it.toDisplay() }

    @Transactional(readOnly = true)
    fun getMenuDisplays(menuName: String): List<MenuDisplay> =
        menuRepository.findMenusByNameContains(menuName).map { it.toDisplay() }
}