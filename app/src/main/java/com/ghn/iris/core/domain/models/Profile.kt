package com.ghn.iris.core.domain.models

data class Profile(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profileImageBase64: String,
    val bannerBase64: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
)
