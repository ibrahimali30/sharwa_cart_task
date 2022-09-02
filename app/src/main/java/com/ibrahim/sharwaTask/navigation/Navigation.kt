package com.ibrahim.sharwaTask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahim.sharwaTask.home.CartScreen
import com.ibrahim.sharwaTask.home.HomeScreen


@Composable
fun Navigation(modifier: Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("cart") {
            CartScreen()
        }
    }
}