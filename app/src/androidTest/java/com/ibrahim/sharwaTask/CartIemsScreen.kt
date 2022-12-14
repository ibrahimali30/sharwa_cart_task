package com.ibrahim.sharwaTask

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.presentation.viewmodel.ItemsState
import com.ibrahim.sharwaTask.home.CartScreen
import com.ibrahim.sharwaTask.home.HomeScreen
import com.ibrahim.sharwaTask.ui.theme.TaskTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CartIemsScreen {

    @get:Rule
    val composeTestRule = createComposeRule()
    val itemsRemoteDataSource = ItemsRemoteDataSource()


    @Test
    fun are_menu_items_shown() {
        val items = itemsRemoteDataSource.getMokedList()
        items.forEach { it.isAddedToCart = true }
        items.first().isLoading = true

        composeTestRule.setContent {
            TaskTheme() {
                val state = remember {
                    ItemsState(
                        items = items
                    )
                }
                CartScreen(
                    items = state.items,
                    onAddToCartClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithText(items.first().name).assertIsDisplayed()
        composeTestRule.onNodeWithText(items.first().decscriptionText).assertIsDisplayed()
        composeTestRule.onNodeWithText("${items.first().price} ${items.first().currecy}").assertIsDisplayed()
        composeTestRule.onNodeWithText("Yor Cart IS Empty").assertDoesNotExist()
    }

    @Test
    fun are_empty_msg_shown() {
        val items = listOf<MenuItem>()
        val state = mutableStateOf(ItemsState(
            items = items
        ))
        composeTestRule.setContent {
            TaskTheme() {

                CartScreen(
                    items = state.value.items,
                    onAddToCartClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Yor Cart IS Empty").assertIsDisplayed()

        state.value = ItemsState()
        state.value = state.value.copy(items = itemsRemoteDataSource.getMokedList())

        composeTestRule.onNodeWithText("Yor Cart IS Empty").assertDoesNotExist()


    }



}