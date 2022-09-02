package com.ibrahim.sharwaTask

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.presentation.viewmodel.ItemsState
import com.ibrahim.sharwaTask.home.HomeScreen
import com.ibrahim.sharwaTask.ui.theme.TaskTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MenuItemshome {

    @get:Rule
    val composeTestRule = createComposeRule()
    val itemsRemoteDataSource = ItemsRemoteDataSource()


    @Test
    fun are_menu_items_shown() {
        val items = itemsRemoteDataSource.getMokedList()

        composeTestRule.setContent {
            TaskTheme() {
                val state = remember {
                    ItemsState(
                        items = items
                    )
                }
                HomeScreen(
                    items = state.items,
                    onAddToCartClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithText(items.first().name).assertIsDisplayed()
        composeTestRule.onNodeWithText(items.first().decscriptionText).assertIsDisplayed()
        composeTestRule.onNodeWithText("${items.first().price} ${items.first().currecy}").assertIsDisplayed()
    }



}