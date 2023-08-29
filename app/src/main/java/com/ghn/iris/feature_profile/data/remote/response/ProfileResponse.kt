package com.ghn.iris.feature_profile.data.remote.response

import com.ghn.iris.feature_profile.domain.model.Profile


data class ProfileResponse(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureBase64: String,
    val profileBannerBase64: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
) {
    fun toProfile(): Profile {
        return Profile(
            userId = userId,
            username = username,
            bio = bio,
            followingCount = followingCount,
            followerCount = followerCount,
            postCount = postCount,
            profileImageBase64 = profilePictureBase64,
            profileBannerBase64 = profileBannerBase64,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}
