package com.ibrahim.sharwaTask.cart.domain.entity





class ItemsResponse : ArrayList<ItemsResponseItem>()

data class ItemsResponseItem(
    val icon: String,
    val id: String,
    val menuCategory: List<MenuItem>,
    val name: String
)

data class MenuItem(
    val currecy: String,
    val decscriptionText: String,
    val icon: String,
    val id: String,
    val name: String,
    val price: Int,
    var isAddedToCart: Boolean = false
)