package com.ibrahim.sharwaTask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahim.sharwaTask.cart.presentation.viewmodel.ItemsState
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.home.CartScreen
import com.ibrahim.sharwaTask.home.HomeScreen


@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController, state: ItemsState,
    onAddToCartClicked: (MenuItem) -> Unit = {}
) {
    NavHost(modifier = modifier, navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(state.items, onAddToCartClicked)
        }
        composable("cart") {
            CartScreen(state.cartTtems, onAddToCartClicked)
        }
    }
}