package com.ibrahim.sharwaTask.home

import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.ibrahim.sharwaTask.cart.MenuItem
import com.ibrahim.sharwaTask.ui.theme.Green
import com.ibrahim.sharwaTask.ui.theme.Yellow


@Composable
fun HomeScreen(
    state: ItemsState,
    onAddToCartClicked: (MenuItem) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        items(state.items){ menuItem->
            Card(Modifier.padding(8.dp)) {
                Column(Modifier.padding(8.dp)) {

                    Text(text = menuItem.name, fontSize = 20.sp, color = Color.Black)
                    Text(text = menuItem.decscriptionText, fontSize = 14.sp, color = Color.Gray)
                    Text(text = "${menuItem.price} ${menuItem.currecy}", fontSize = 16.sp, color = Green)

                    val pg = if (menuItem.isAddedToCart) Color.Red else Yellow
                    val ptIcon = if (menuItem.isAddedToCart) Icons.Default.Delete else Icons.Default.Add
                    val text = if (menuItem.isAddedToCart) "Remove from cart" else "Add to cart"
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        onClick = { onAddToCartClicked(menuItem) },
                        colors = buttonColors(
                            backgroundColor = pg
                        )

                    ) {
                        Row() {
                            Icon(
                                imageVector = ptIcon,
                                "",
                                tint = Color.White
                            )

                            Text(text = text, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
