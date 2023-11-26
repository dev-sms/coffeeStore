package com.example.coffeeStore.menu.service

import com.example.coffeeStore.menu.domain.BestMenu
import com.example.coffeeStore.menu.domain.Menu
import com.example.coffeeStore.menu.domain.SpecialMenuType
import com.example.coffeeStore.menu.dto.*
import com.example.coffeeStore.menu.repository.BestMenuRepository
import com.example.coffeeStore.menu.repository.MenuRepository
import com.example.coffeeStore.order.domain.OrderMenu
import com.example.coffeeStore.order.repository.OrderMenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.example.coffeeStore.order.dto.OrderMenuInfo
import com.fasterxml.jackson.databind.JsonSerializer.None
import java.time.LocalDateTime

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val recipeService: RecipeService,
    private val orderMenuRepository: OrderMenuRepository,
    private val bestMenuRepository: BestMenuRepository
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
    fun getBestMenu(): List<Pair<Menu, Int>> {
        val orderMenus = orderMenuRepository.findAll()
        val bestMenus: MutableMap<Menu, Int> = mutableMapOf()

        val currentMonthStart = LocalDateTime.now().withDayOfMonth(1)
            .withHour(0).withMinute(0).withSecond(0)
        val currentMonthEnd = LocalDateTime.now().withDayOfMonth(LocalDateTime.now().month
            .length(LocalDateTime.now().toLocalDate().isLeapYear())).withHour(23)
            .withMinute(59).withSecond(59)

        for (orderMenu in orderMenus) {
            bestMenus[orderMenu.menu] = bestMenus.getOrDefault(orderMenu.menu, 0) + orderMenu.quantity
        }
        val bestMenu: List<Pair<Menu, Int>> = bestMenus.toList().sortedByDescending { it.second }.take(2)
        if(bestMenuRepository.
            findAllByCreatedAtBetween(
                currentMonthStart, currentMonthEnd)
                .isNotEmpty()){
            bestMenuRepository.deleteByCreatedAtBetween(currentMonthStart, currentMonthEnd)
        }
        bestMenuRepository.save(BestMenu(menuRank = 1, name = bestMenu[0].first.name, quantity = bestMenu[0].second))
        bestMenuRepository.save(BestMenu(menuRank = 2, name = bestMenu[1].first.name, quantity = bestMenu[1].second))
        return bestMenu
    }

    @Transactional
    fun updateSpecialMenuType(menuId: Long, specialMenuType: SpecialMenuType){
        val menu = getMenuById(menuId)
        menu.specialMenuType = specialMenuType
    }


    fun getMenuById(menuId: Long): Menu = menuRepository.findMenuById(menuId)
        ?: throw NoSuchElementException("해당 메뉴를 찾을 수 없습니다. : $menuId")
}