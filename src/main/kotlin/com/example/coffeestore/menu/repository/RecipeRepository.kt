package com.example.coffeeStore.menu.repository

import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.Recipe
import org.springframework.data.jpa.repository.JpaRepository

interface RecipeRepository: JpaRepository<Recipe, Long> {
    fun findRecipeById(id: Long): Recipe?
    fun findRecipesByMenu(menu: Menu): List<Recipe>
}