package com.ghn.iris.core.domain.models

data class User(
    val userId: String,
    val profileImageBase64: String,
    val username: String,
    val description: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)
