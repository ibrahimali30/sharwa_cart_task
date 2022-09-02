package com.ibrahim.sharwaTask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahim.sharwaTask.cart.ItemsState
import com.ibrahim.sharwaTask.cart.MenuItem
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
            HomeScreen(state, onAddToCartClicked)
        }
        composable("cart") {
            CartScreen(state, onAddToCartClicked)
        }
    }
}