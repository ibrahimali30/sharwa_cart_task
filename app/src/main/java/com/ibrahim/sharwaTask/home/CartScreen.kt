package com.ibrahim.sharwaTask.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahim.sharwaTask.cart.ItemsState
import com.ibrahim.sharwaTask.cart.ItemsViewModel
import com.ibrahim.sharwaTask.cart.MenuItem


@Composable
fun CartScreen(
    items: List<MenuItem>,
    onAddToCartClicked: (MenuItem) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        items(items){ menuItem->
            MenuItemComposable(menuItem, onAddToCartClicked)
        }
    }
}