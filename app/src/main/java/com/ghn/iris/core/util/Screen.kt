package com.ghn.iris.core.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
    object NotificationScreen : Screen("notification_screen")
    object ProfileScreen : Screen("profile_screen")
    object CreatePostScreen : Screen("person_list_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object ChatScreen : Screen("chat_screen")
    object MessagesScreen : Screen("messages_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
}