package com.example.coffeestore.menu.controller

import com.example.coffeestore.menu.service.MenuService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {
    @GetMapping
    fun showMenus(
        @RequestParam("menu", required = false) menuName: String?, model: ModelMap
    ): String {
        model["menus"] = menuName?.let { menuService.getMenuDisplays(it) } ?: menuService.getMenuDisplays()
        return "menu/list"
    }
}