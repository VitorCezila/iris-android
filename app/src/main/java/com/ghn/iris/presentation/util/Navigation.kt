package com.ghn.iris.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghn.iris.presentation.home_screen.HomeScreen
import com.ghn.iris.presentation.login_screen.LoginScreen
import com.ghn.iris.presentation.notification_screen.NotificationScreen
import com.ghn.iris.presentation.profile_screen.ProfileScreen
import com.ghn.iris.presentation.register_screen.RegisterScreen
import com.ghn.iris.presentation.search_screen.SearchScreen
import com.ghn.iris.presentation.splash_screen.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}