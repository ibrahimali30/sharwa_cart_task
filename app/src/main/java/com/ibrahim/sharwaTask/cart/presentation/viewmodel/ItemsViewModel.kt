package com.ibrahim.sharwaTask.cart.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.interactor.CartOperationsUsecase
import com.ibrahim.sharwaTask.cart.domain.interactor.GetItemsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUsecase: GetItemsUsecase,
    private val cartOperationsUsecase: CartOperationsUsecase
): ViewModel(){

    val state: MutableState<ItemsState> = mutableStateOf(ItemsState(ItemsRemoteDataSource().getMokedList()!!.get(0).menuCategory))


    fun addItemToCart(menuItem: MenuItem){
        state.value.items
            .find { it.id == menuItem.id }!!.apply {
                isAddedToCart = !isAddedToCart
            }
        refreshList()

    }



    fun removeItemFromCart(menuItem: MenuItem){
        state.value.items.find { it.id == menuItem.id }!!.isAddedToCart = false
        refreshList()
    }

    fun clearCart(){
        state.value.items.forEach { it.isAddedToCart = false }
        refreshList()
    }

    private fun refreshList() {
        val newState = state.value.copy(items = state.value.items.toMutableStateList())
        state.value = ItemsState()
        state.value = newState
    }

    fun onAddToCartClicked(menuItem: MenuItem) {
        if (menuItem.isAddedToCart)
            removeItemFromCart(menuItem)
        else
            addItemToCart(menuItem)
    }
}

data class ItemsState(
    val items: List<MenuItem> = listOf(),
) {
    val cartCount: Int
        get() = items.filter { it.isAddedToCart }.size


    val cartTtems: List<MenuItem>
        get() = items.filter { it.isAddedToCart }
}

