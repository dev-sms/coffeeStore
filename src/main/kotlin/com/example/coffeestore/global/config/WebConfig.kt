package com.example.coffeeStore.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        with(registry) {
            addViewController("/").setViewName("index")
            addViewController("/user/admin/login").setViewName("admin/login")
            addViewController("/admin").setViewName("admin/index")
            addViewController("/order/complete").setViewName("order/complete")
        }
    }
}