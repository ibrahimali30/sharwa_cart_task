package com.ibrahim.sharwaTask.cart.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.interactor.CartOperationsUsecase
import com.ibrahim.sharwaTask.cart.domain.interactor.GetItemsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUsecase: GetItemsUsecase,
    private val cartOperationsUsecase: CartOperationsUsecase
): ViewModel(){

    val state: MutableState<ItemsState> = mutableStateOf(ItemsState())

    init {
        getMenuItems()
    }

    private fun getMenuItems() {
        getItemsUsecase.excute()
            .onEach {
                state.value = state.value.copy(items = it)
            }.launchIn(viewModelScope)
    }

    fun addItemToCart(menuItem: MenuItem){
        state.value.items.find { it.id == menuItem.id }?.isLoading = true
        refreshList()

        cartOperationsUsecase.addItemToCart(menuItem)
            .onEach {updatedMenuItem->
                state.value.items.find { it.id == updatedMenuItem.id }!!.apply {
                    isAddedToCart = true
                    isLoading = false
                }
                refreshList()
            }.launchIn(viewModelScope)
    }



    fun removeItemFromCart(menuItem: MenuItem){

        state.value.items.find { it.id == menuItem.id }?.isLoading = true
        refreshList()

        cartOperationsUsecase.addItemToCart(menuItem)
            .onEach {updatedMenuItem->
                state.value.items.find { it.id == updatedMenuItem.id }!!.apply {
                    isAddedToCart = false
                    isLoading = false
                }
                refreshList()
            }.launchIn(viewModelScope)
    }

    fun clearCart(){
        cartOperationsUsecase.clearCart()
            .onEach {
                state.value.items.forEach { it.isAddedToCart = false }
                refreshList()
            }.launchIn(viewModelScope)
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

    val price: Int
        get() = cartTtems.sumOf { it.price }


    val cartTtems: List<MenuItem>
        get() = items.filter { it.isAddedToCart }
}

