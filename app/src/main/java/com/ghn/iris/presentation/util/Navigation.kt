package com.ghn.iris.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghn.iris.domain.models.Post
import com.ghn.iris.presentation.create_post_screen.CreatePostScreen
import com.ghn.iris.presentation.home_screen.HomeScreen
import com.ghn.iris.presentation.login_screen.LoginScreen
import com.ghn.iris.presentation.message_screen.MessagesScreen
import com.ghn.iris.presentation.notification_screen.NotificationScreen
import com.ghn.iris.presentation.post_detail_screen.PostDetailScreen
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
            HomeScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
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
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen()
        }
        composable(Screen.MessagesScreen.route) {
            MessagesScreen()
        }
        composable(Screen.PostDetailScreen.route) {
            val mockPost = Post(
                id = "0",
                userId = "0",
                username = "cezila",
                profilePictureUrl = "",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a sem quam. Integer placerat efficitur mattis. Ut magna nunc, dictum rutrum augue ut, condimentum sollicitudin nisl. In est turpis, egestas in ex eu.",
                imageUrl = "",
                formattedTime = "1 hour ago",
                likeCount = 10,
                commentCount = 15,
                sharesCount = 0,
                isLiked = true,
                isSaved = false,
                isOwnPost = true
            )
            PostDetailScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                post = mockPost
            )
        }
    }
}