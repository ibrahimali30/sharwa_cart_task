package com.ibrahim.sharwaTask

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ibrahim.sharwaTask.cart.presentation.viewmodel.ItemsViewModel
import com.ibrahim.sharwaTask.home.BottomNavigationBar
import com.ibrahim.sharwaTask.cart.presentation.navigation.Navigation
import com.ibrahim.sharwaTask.test.TAG_MENU_Cart
import com.ibrahim.sharwaTask.test.TAG_MENU_HOME
import com.ibrahim.sharwaTask.ui.navigation.Screen
import com.ibrahim.sharwaTask.ui.theme.BackGround
import com.ibrahim.sharwaTask.ui.theme.TaskTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<ItemsViewModel>()

            TaskTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        Card(
                            Modifier,
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        ) {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = Screen.Home,
                                        uiTestTag = TAG_MENU_HOME,
                                        icon = Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        name = "Cart",
                                        route = Screen.Cart,
                                        uiTestTag = TAG_MENU_Cart,
                                        icon = Icons.Default.ShoppingCart,
                                        badgeCount = viewModel.state.value.cartCount
                                    )
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route.routeName)
                                }
                            )
                        }
                    }
                ) {
                    Navigation(
                        modifier = Modifier.background(color = BackGround).padding(bottom = it.calculateBottomPadding()),
                        navController = navController,
                        state = viewModel.state.value,
                        onAddToCartClicked = {
                            viewModel.onAddToCartClicked(it)
                        },
                        onAClearClicked = {
                            viewModel.clearCart()
                        },
                    )
                }
            }

        }
    }
}



