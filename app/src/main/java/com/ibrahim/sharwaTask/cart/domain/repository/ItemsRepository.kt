package com.ibrahim.sharwaTask.cart.domain.repository

import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getItems():  Flow<List<MenuItem>>
    fun addItemToCart(menuItem: MenuItem): Flow<MenuItem>
    fun removeItemFromCart(menuItem: MenuItem): Flow<MenuItem>
    fun clearCart(): Flow<Boolean>

}