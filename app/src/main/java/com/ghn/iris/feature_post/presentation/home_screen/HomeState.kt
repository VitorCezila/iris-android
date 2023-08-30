package com.ghn.iris.feature_post.presentation.home_screen

import com.ghn.iris.feature_profile.domain.model.Profile

data class HomeState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPosts: Boolean = false,
    val profile: Profile? = null
)
