package com.example.coffeeStore.user.service

import com.example.coffeeStore.menu.domain.BestMenu
import com.example.coffeeStore.menu.repository.BestMenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StatService (
    val bestMenuRepository: BestMenuRepository
){
    @Transactional
    fun getBestMenus(): List<BestMenu> {
        return bestMenuRepository.findAll()
    }
}