package com.example.coffeeStore.menu.service

import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.Recipe
import com.example.coffeeStore.menu.dto.RecipeForm
import com.example.coffeeStore.menu.repository.RecipeRepository
import com.example.coffeeStore.order.domain.OrderMenu
import com.example.coffeeStore.order.exception.OutOfStockException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IngredientService
) {

    @Transactional
    fun addRecipesToMenu(recipeForms: List<RecipeForm>, menu: Menu): List<Recipe> =
        recipeRepository.saveAll(recipeForms.map {
            it.toEntity(
                menu,
                ingredientService.getIngredientById(it.ingredientId)
            )
        })

    fun getRecipesOfMenu(menu: Menu): List<Recipe> = recipeRepository.findRecipesByMenu(menu)

    fun getRecipeById(recipeId: Long): Recipe =
        recipeRepository.findRecipeById(recipeId) ?: throw NoSuchElementException("해당 레시피를 찾을 수 없습니다 : $recipeId")

    @Transactional
    fun removeRecipe(recipeId: Long) {
        val recipe = getRecipeById(recipeId)
        recipeRepository.delete(recipe)
    }

    @Transactional
    fun updateAmountOfRecipe(recipeId: Long, amount: Double) {
        val recipe = getRecipeById(recipeId)
        recipe.amount = amount
    }

    @Transactional
    fun updateRecipesOnOrder(orderMenu: OrderMenu) {
        orderMenu.menu.recipes.forEach { updateIngredientStock(it.ingredient, orderMenu.quantity * it.amount) }
    }

    private fun updateIngredientStock(ingredient: Ingredient, deduction: Double) {
        if (ingredient.stock < deduction) throw OutOfStockException(ingredient.name)
        ingredient.stock -= deduction
    }
}