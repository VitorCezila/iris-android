package com.ghn.iris.feature_profile.presentation.profile_screen

import com.ghn.iris.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val isLogoutDialogVisible: Boolean = false
)
