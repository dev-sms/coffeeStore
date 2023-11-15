package com.example.coffeeStore.menu.service

import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.menu.dto.AmountForm
import com.example.coffeeStore.menu.repository.IngredientRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) {

    fun getAllIngredients(): List<Ingredient> {
        return ingredientRepository.findAll()
    }

    fun saveIngredient(ingredient: Ingredient) {
        ingredientRepository.save(ingredient)
    }

    @Transactional
    fun updateAmount(amountForm: AmountForm) {
        getIngredientById(amountForm.ingredientId).stock = amountForm.amount
    }

    fun getIngredientById(ingredientId: Long): Ingredient =
        ingredientRepository.findIngredientById(ingredientId)
            ?: throw NoSuchElementException("해당 재료를 찾을 수 없습니다 : $ingredientId")
}