package com.example.coffeeStore.menu.service

import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.SpecialMenuType
import com.example.coffeeStore.menu.dto.*
import com.example.coffeeStore.menu.repository.MenuRepository
import com.example.coffeeStore.order.repository.OrderMenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.example.coffeeStore.order.dto.OrderMenuInfo
import com.fasterxml.jackson.databind.JsonSerializer.None

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val recipeService: RecipeService,
    private val orderMenuRepository: OrderMenuRepository
) {

    @Transactional(readOnly = true)
    fun getMenuDisplays(): List<MenuDisplay> = menuRepository.findAll().map { it.toDisplay() }

    @Transactional(readOnly = true)
    fun getMenuDisplays(menuName: String): List<MenuDisplay> =
        menuRepository.findMenusByNameContains(menuName).map { it.toDisplay() }

    @Transactional(readOnly = true)
    fun getMenuDisplay(menuId: Long): MenuDisplay = getMenuById(menuId).toDisplay()

    @Transactional
    fun createMenu(menuForm: MenuForm) {
        val menu = menuRepository.save(menuForm.toEntity())
        recipeService.addRecipesToMenu(menuForm.recipes, menu)
    }

    @Transactional(readOnly = true)
    fun getRecipesOfMenu(menuId: Long): List<RecipeInfo> {
        val menu = getMenuById(menuId)
        return recipeService.getRecipesOfMenu(menu).map { it.toRecipeInfo() }
    }

    @Transactional
    fun removeMenu(menuId: Long) {
        val menu = getMenuById(menuId)
        menuRepository.delete(menu)
    }

    @Transactional
    fun addRecipes(menuId: Long, recipeForm: NewRecipeForm) {
        val menu = getMenuById(menuId)
        recipeService.addRecipesToMenu(recipeForm.recipes, menu)
    }

    @Transactional
    fun updateSpecialMenu(menuId: Long, specialMenu: Boolean) {
        val menu = getMenuById(menuId)
        menu.specialMenu = specialMenu
    }

    @Transactional
    fun getBestMenu() {
        val orderMenuList = orderMenuRepository.findAll()
        val orderedMenuName = orderMenuList.map { it.menu.name }
    }

    @Transactional
    fun updateSpecialMenuType(menuId: Long, specialMenuType: SpecialMenuType){
        val menu = getMenuById(menuId)
        menu.specialMenuType = specialMenuType
    }


    fun getMenuById(menuId: Long): Menu = menuRepository.findMenuById(menuId)
        ?: throw NoSuchElementException("해당 메뉴를 찾을 수 없습니다. : $menuId")
}