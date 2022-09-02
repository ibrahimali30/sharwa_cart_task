package com.ibrahim.sharwaTask

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.data.ItemsRepositoryImpl
import com.ibrahim.sharwaTask.cart.domain.repository.ItemsRepository
import com.ibrahim.sharwaTask.cart.presentation.viewmodel.ItemsViewModel
import com.ibrahim.sharwaTask.di.AppModule
import com.ibrahim.sharwaTask.home.BottomNavigationBar
import com.ibrahim.sharwaTask.home.CartScreen
import com.ibrahim.sharwaTask.home.HomeScreen
import com.ibrahim.sharwaTask.ui.navigation.Screen
import com.ibrahim.sharwaTask.ui.theme.TaskTheme
import com.ibrahim.sharwaTask.MainActivity
import com.ibrahim.sharwaTask.test.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton


@RunWith(AndroidJUnit4::class)
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@UninstallModules(AppModule::class)
@HiltAndroidTest
class CartFullUiTest {

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Provides
        @Singleton
        fun provideItemsRemoteDataSource(): ItemsRemoteDataSource {
            return ItemsRemoteDataSource()
        }

        @Provides
        @Singleton
        fun provideItemsRepository (itemsRemoteDataSource: ItemsRemoteDataSource): ItemsRepository {
            return ItemsRepositoryImpl(itemsRemoteDataSource)
        }
    }

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    val itemsRemoteDataSource = ItemsRemoteDataSource()
    lateinit var viewModel: ItemsViewModel



    @OptIn(ExperimentalMaterialApi::class)
    @Before
    fun before(){

        composeTestRule.activity.setContent {
            viewModel = hiltViewModel()

            TaskTheme() {
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
                    NavHost(
                        modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                        navController = navController,
                        startDestination = Screen.Home.routeName,
                        builder = {
                            composable(
                                route = Screen.Home.routeName,
                            ){
                                HomeScreen(
                                    items = viewModel.state.value.items,
                                    onAddToCartClicked = {
                                        viewModel.onAddToCartClicked(it)
                                    }
                                )
                            }

                            composable(
                                route = Screen.Cart.routeName,
                            ){
                                CartScreen(
                                    items = viewModel.state.value.cartTtems,
                                    onAddToCartClicked = {
                                        viewModel.onAddToCartClicked(it)
                                    }, onAClearClicked = {
                                        viewModel.clearCart()
                                    }
                                )
                            }
                        }
                    )
                }


            }
        }
    }
    
    @Test
    fun sdfdfdf(){
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAGGGG") // For learning the ui tree system

//        composeTestRule.onNodeWithTag(TAG_MENU_HOME).performClick()
        composeTestRule.onAllNodesWithTag(TAG_Item_Name, useUnmergedTree = true).onFirst().assertTextEquals(
            "Desert 1",
        )

        val button = composeTestRule.onAllNodesWithTag(TAG_Add_To_Cart).onFirst()
        button.assertTextEquals("Add to cart")

        val progress = composeTestRule.onAllNodesWithTag(TAG_Add_To_Cart).onFirst()

        button.performClick()

        progress.assertIsDisplayed()

        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TAG_Add_To_Cart_Progress),
            timeoutMillis = 3000
        )
        button.assertTextEquals("Remove from cart")


//        composeTestRule.onAllNodesWithTag(TAG_Add_To_Cart).onFirst().assertIsNotDisplayed()


        composeTestRule.onNodeWithTag(TAG_MENU_Cart).performClick()

        composeTestRule.onAllNodesWithTag(TAG_Item_Name, useUnmergedTree = true).onFirst().assertTextEquals(
            "Desert 1",
        )
        composeTestRule.onAllNodesWithTag(TAG_Add_To_Cart).onFirst().assertTextEquals("Remove from cart")


    }




}




fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    return this.waitUntilNodeCount(matcher, 1, timeoutMillis)
}

fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    return this.waitUntilNodeCount(matcher, 0, timeoutMillis)
}

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = 1_000L
) {
    this.waitUntil(timeoutMillis) {
        this.onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}