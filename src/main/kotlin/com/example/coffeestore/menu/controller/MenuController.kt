package com.example.coffeestore.menu.controller

import com.example.coffeestore.menu.service.MenuService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {

}