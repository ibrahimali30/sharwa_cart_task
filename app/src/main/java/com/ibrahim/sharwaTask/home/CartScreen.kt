package com.ibrahim.sharwaTask.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ibrahim.sharwaTask.cart.ItemsState
import com.ibrahim.sharwaTask.cart.ItemsViewModel
import com.ibrahim.sharwaTask.cart.MenuItem


@Composable
fun CartScreen(
    state: ItemsState,
    onAddToCartClicked: (MenuItem) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Cart screen")
    }
}