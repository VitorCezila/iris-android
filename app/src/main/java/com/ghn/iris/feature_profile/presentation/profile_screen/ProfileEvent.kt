package com.ghn.iris.feature_profile.presentation.profile_screen

import com.ghn.iris.core.domain.models.Post

sealed class ProfileEvent {
    data class ToggleFollowState(val userId: String): ProfileEvent()
    data class LikePost(val postId: String): ProfileEvent()
    data class DeletePost(val post: Post): ProfileEvent()
    object DismissLogoutDialog: ProfileEvent()
    object ShowLogoutDialog: ProfileEvent()
    object Logout: ProfileEvent()
}
