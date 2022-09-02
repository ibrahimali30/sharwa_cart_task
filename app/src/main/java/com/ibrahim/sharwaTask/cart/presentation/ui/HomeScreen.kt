package com.ibrahim.sharwaTask.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.test.TAG_Add_To_Cart
import com.ibrahim.sharwaTask.test.TAG_Add_To_Cart_Progress
import com.ibrahim.sharwaTask.test.TAG_Item_Name
import com.ibrahim.sharwaTask.ui.theme.Green
import com.ibrahim.sharwaTask.ui.theme.Yellow


@Composable
fun HomeScreen(
    items: List<MenuItem> = listOf(),
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

@Composable
fun MenuItemComposable(menuItem: MenuItem, onAddToCartClicked: (MenuItem) -> Unit) {
    Card(Modifier.padding(8.dp)) {
        Column(Modifier.padding(8.dp)) {

            Text(modifier = Modifier.testTag(TAG_Item_Name), text = menuItem.name, fontSize = 20.sp, color = Color.Black)
            Text(text = menuItem.decscriptionText, fontSize = 14.sp, color = Color.Gray)
            Text(text = "${menuItem.price} ${menuItem.currecy}", fontSize = 16.sp, color = Green)

            val pg = if (menuItem.isAddedToCart) Color.Red else Yellow
            val ptIcon = if (menuItem.isAddedToCart) Icons.Default.Delete else Icons.Default.Add
            val text = if (menuItem.isAddedToCart) "Remove from cart" else "Add to cart"
            Button(
                modifier = Modifier.testTag(TAG_Add_To_Cart)
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                onClick = { onAddToCartClicked(menuItem) },
                colors = buttonColors(
                    backgroundColor = pg
                )

            ) {
                if (menuItem.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(14.dp).testTag(TAG_Add_To_Cart_Progress),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                }else{
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
