package com.ghn.iris.core.util

import com.ghn.iris.feature_profile.domain.model.Profile

interface ProfileFollower {

    suspend fun toggleFollow(
        profile: Profile,
        onRequest: suspend (isFollow: Boolean) -> SimpleResource,
        onStateUpdated: (List<Profile>) -> Unit
    )

}