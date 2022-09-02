package com.ibrahim.sharwaTask.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ibrahim.sharwaTask.BottomNavItem


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route.routeName == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                modifier = Modifier.testTag(item.uiTestTag),
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount >= 0) {

                            Badge {
                                Text(item.badgeCount.toString())
                            }
                        }
                    }) {
                        Icon(
                            item.icon,
                            contentDescription = ""
                        )
                    }

                }
            )
        }
    }
}