package com.composetut.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.composetut.ui.home.HomeScreen
import com.composetut.ui.login.LoginScreen
import com.composetut.ui.splash.SplashScreen

@Composable
fun AppNavigation(){
val rememberNav= rememberNavController()
    NavHost(
        navController = rememberNav,
        startDestination = NavigationItem.SPLASH.route
    ){
        composable(NavigationItem.SPLASH.route){
            SplashScreen(rememberNav)
        }
        composable(NavigationItem.LOGIN.route){
            LoginScreen(rememberNav)
        }
        composable(NavigationItem.HOME.route){
            HomeScreen(rememberNav)
        }
    }

}