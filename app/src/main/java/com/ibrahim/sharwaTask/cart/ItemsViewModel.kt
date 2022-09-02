package com.ibrahim.sharwaTask.cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ItemsViewModel @Inject constructor(

): ViewModel(){

    val state: MutableState<ItemsState> = mutableStateOf(ItemsState(getMokedList()))


    init {
        viewModelScope.launch {
            delay(5000)
            clearCart()
        }
    }


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


data class MenuItem(
    val id: String,
    val name: String,
    val icon: String,
    val decscriptionText: String,
    val price: Int,
    val currecy: String,
    var isAddedToCart: Boolean = false
)

fun getMokedList() = listOf(
    MenuItem("1","name","icon","decscriptionText",1,"currecy"),
    MenuItem("2","name","icon","decscriptionText",1,"currecy"),
    MenuItem("3","name","icon","decscriptionText",1,"currecy"),
    MenuItem("4","name","icon","decscriptionText",1,"currecy"),
    MenuItem("5","name","icon","decscriptionText",1,"currecy"),
    MenuItem("6","name","icon","decscriptionText",1,"currecy"),
    MenuItem("7","name","icon","decscriptionText",1,"currecy"),
)