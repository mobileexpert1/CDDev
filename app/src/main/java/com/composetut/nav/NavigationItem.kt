package com.composetut.nav

sealed class NavigationItem (val route:String){
    data object SPLASH:NavigationItem(Screens.SPLASH.name)
    data object LOGIN:NavigationItem(Screens.LOGIN.name)
    data object HOME:NavigationItem(Screens.HOME.name)
}