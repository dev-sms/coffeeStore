package com.example.coffeeStore.vendor.controller

import com.example.coffeeStore.menu.domain.Ingredient
import com.example.coffeeStore.menu.service.IngredientService
import com.example.coffeeStore.vendor.domain.Vendor
import com.example.coffeeStore.vendor.dto.ActualDeliveryDateForm
import com.example.coffeeStore.vendor.dto.SupplyForm
import com.example.coffeeStore.vendor.service.SupplyService
import com.example.coffeeStore.vendor.service.VendorService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("supply")
class SupplyController(
    private val supplyService: SupplyService,
    private val vendorService: VendorService,
    private val ingredientService: IngredientService
) {
    @ModelAttribute("vendors")
    fun getAllVendors(): List<Vendor> = vendorService.getVendorList()

    @ModelAttribute("ingredients")
    fun getAllIngredients(): List<Ingredient> = ingredientService.getAllIngredients()

    @GetMapping
    fun showSupplyHistory(model: ModelMap): String {
        model["supplies"] = supplyService.getSupplyInfoList()
        return "supply/info"
    }

    @GetMapping("/{supplyId}")
    fun showSupplyDetail(
        @PathVariable("supplyId") supplyId: Long,
        model: ModelMap
    ): String {
        model["supply"] = supplyService.getSupplyDetail(supplyId)
        return "supply/detail"
    }

    @PostMapping("delivery-date")
    fun addActualDeliveryDate(form: ActualDeliveryDateForm): String {
        supplyService.updateActualDeliveryDate(form)
        return "redirect:/supply"
    }

    @GetMapping("/new")
    fun showSupplyForm(model: ModelMap): String {
        return "supply/new"
    }

    @PostMapping("/new")
    fun addSupply(form: SupplyForm): String {
        supplyService.addSupply(form)
        return "redirect:/supply"
    }
}