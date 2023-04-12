package com.ghn.iris.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.domain.models.Profile
import com.ghn.iris.core.presentation.util.Screen
import com.ghn.iris.feature_auth.presentation.login_screen.LoginScreen
import com.ghn.iris.feature_auth.presentation.register_screen.RegisterScreen
import com.ghn.iris.feature_auth.presentation.splash_screen.SplashScreen
import com.ghn.iris.feature_chat.presentation.message_screen.MessagesScreen
import com.ghn.iris.feature_notification.presentation.NotificationScreen
import com.ghn.iris.feature_post.presentation.create_post_screen.CreatePostScreen
import com.ghn.iris.feature_post.presentation.home_screen.HomeScreen
import com.ghn.iris.feature_post.presentation.post_detail_screen.PostDetailScreen
import com.ghn.iris.feature_profile.presentation.edit_profile_screen.EditProfileScreen
import com.ghn.iris.feature_profile.presentation.profile_screen.ProfileScreen
import com.ghn.iris.feature_profile.presentation.search_screen.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = Screen.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = Screen.HomeScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState,
                onPopBackStack = navController::popBackStack
            )
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                imageLoader = imageLoader,
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.MessagesScreen.route) {
            MessagesScreen()
        }
        composable(Screen.PostDetailScreen.route) {
            val mockPost = Post(
                id = "0",
                userId = "0",
                username = "cezila",
                profileImageBase64 = "",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a sem quam. Integer placerat efficitur mattis. Ut magna nunc, dictum rutrum augue ut, condimentum sollicitudin nisl. In est turpis, egestas in ex eu.",
                imageBase64 = "",
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
        composable(Screen.EditProfileScreen.route) {
            val mockProfile = Profile(
                userId = "0",
                username = "cezila",
                profileImageBase64 = "",
                bannerBase64 = "",
                bio = "Writer by Profession. Artist by Passion!",
                followerCount = 2467,
                followingCount = 1589,
                postCount = 5,
                isFollowing = false,
                isOwnProfile = true
            )
            EditProfileScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                profile = mockProfile
            )
        }
    }
}