package com.ibrahim.sharwaTask.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem


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