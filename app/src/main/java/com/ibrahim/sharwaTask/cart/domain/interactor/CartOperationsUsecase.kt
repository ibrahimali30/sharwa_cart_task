package com.ibrahim.sharwaTask.cart.domain.interactor

import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CartOperationsUsecase @Inject constructor(private val repository: ItemsRepository) {

    fun addItemToCart(menuItem: MenuItem): Flow<MenuItem>{
        return repository.addItemToCart(menuItem)
    }

    fun removeItemFromCart(menuItem: MenuItem): Flow<MenuItem>{
        return repository.removeItemFromCart(menuItem)
    }

    fun clearCart(): Flow<Boolean>{
        return repository.clearCart()
    }
}