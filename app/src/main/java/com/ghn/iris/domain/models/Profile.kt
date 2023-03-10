package com.ghn.iris.domain.models

data class Profile(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val bannerUrl: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
)
