package com.ibrahim.sharwaTask

import androidx.compose.ui.graphics.vector.ImageVector
import com.ibrahim.sharwaTask.ui.navigation.Screen


data class BottomNavItem(
    val name: String,
    val route: Screen,
    val uiTestTag: String,
    val icon: ImageVector,
    val badgeCount: Int = -1
)








