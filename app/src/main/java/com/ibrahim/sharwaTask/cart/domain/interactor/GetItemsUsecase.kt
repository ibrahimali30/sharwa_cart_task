package com.ibrahim.sharwaTask.cart.domain.interactor

import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetItemsUsecase @Inject constructor(private val repository: ItemsRepository) {

    fun excute(): Flow<List<MenuItem>> {
        return repository.getItems()
    }
}