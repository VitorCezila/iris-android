package com.ghn.iris.feature_post.presentation.home_screen

data class HomeState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPosts: Boolean = false,
)
