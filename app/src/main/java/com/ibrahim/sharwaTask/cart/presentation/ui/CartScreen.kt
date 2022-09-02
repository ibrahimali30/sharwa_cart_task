package com.ibrahim.sharwaTask.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.ui.theme.Green
import com.ibrahim.sharwaTask.ui.theme.Yellow


@SuppressLint("SuspiciousIndentation")
@Composable
fun CartScreen(
    items: List<MenuItem>,
    price: Int = 0,
    onAddToCartClicked: (MenuItem) -> Unit = {},
    onAClearClicked: () -> Unit = {}
) {

    if (items.isEmpty()){
        EmptyCartVompose()
    }

    Column() {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 8.dp)
        ) {
            items(items){ menuItem->
                MenuItemComposable(menuItem, onAddToCartClicked)
            }


        }

        if (items.isNotEmpty())
            Card(Modifier.padding(8.dp)) {
                Column(Modifier.padding(8.dp)) {

                    Text(text = "Total of : $price", color = Color.Black, fontSize = 18.sp)

                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black
                        ),
                        onClick = {
                            onAClearClicked()
                        }

                    ) {
                        Text(text = "Clear Cart", color = Color.White)

                    }
                }
            }
    }

}

@Composable
fun EmptyCartVompose() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Yor Cart IS Empty", fontSize = 22.sp)
    }
}
