package com.example.coffeeStore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeStoreApplication

fun main(args: Array<String>) {
    runApplication<CoffeeStoreApplication>(*args)
}
