package com.ibrahim.sharwaTask.cart.data

import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.repository.ItemsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemsRepositoryImpl@Inject constructor(
    private val itemsRemoteDataSource: ItemsRemoteDataSource
): ItemsRepository {
    override fun getItems(): Flow<List<MenuItem>> = flow{
        emit(itemsRemoteDataSource.getMokedList())
    }

    override fun addItemToCart(menuItem: MenuItem): Flow<MenuItem> = flow{
        delay(2000) // simulate network call
        emit(menuItem.copy(isAddedToCart = true))
    }

    override fun removeItemFromCart(menuItem: MenuItem): Flow<MenuItem> = flow{
        delay(2000) // simulate network call
        emit(menuItem.also { it.isAddedToCart = false })
    }

    override fun clearCart(): Flow<Boolean> = flow{
        emit(true)
    }

}