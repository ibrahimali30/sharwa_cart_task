package com.testUtil

import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem


fun getTestMenuItemsList(): List<MenuItem> {
    return listOf(
        MenuItem(id = "id", icon = "icon", currecy = "SR", name = "name", price = 100, decscriptionText = "description")
    )
}