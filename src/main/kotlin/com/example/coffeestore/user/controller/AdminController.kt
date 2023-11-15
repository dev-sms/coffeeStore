package com.example.coffeeStore.user.controller

import com.example.coffeeStore.user.dto.AdminRegistrationForm
import com.example.coffeeStore.user.service.AdminService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user/admin")
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping("/new")
    fun registrationForm(): String {
        return "admin/registration"
    }

    @PostMapping("/new")
    fun processRegistration(registrationForm: AdminRegistrationForm): String {
        adminService.processRegistration(registrationForm)
        return "/"
    }
}