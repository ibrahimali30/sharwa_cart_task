package com.ibrahim.sharwaTask.ui.navigation



sealed class Screen(val routeName: String){

    object Home: Screen(routeName = "home")

    object Cart: Screen(routeName = "cart")

}

