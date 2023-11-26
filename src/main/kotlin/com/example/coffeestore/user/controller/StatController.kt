package com.example.coffeeStore.user.controller

import com.example.coffeeStore.user.service.StatService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/stat")
class StatController (
    private val statService: StatService
){
    @GetMapping
    fun showStatistic(model: ModelMap): String {
        model["bestMenus"] = statService.getBestMenus()
        return "admin/stat"
    }
}