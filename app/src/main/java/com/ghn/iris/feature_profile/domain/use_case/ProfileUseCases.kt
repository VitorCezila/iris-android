package com.ghn.iris.feature_profile.domain.use_case

import com.ghn.iris.core.domain.use_case.ToggleFollowStateForUserUseCase

class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val updateProfile: UpdateProfileUseCase,
    val getPostsForProfile: GetPostsForProfileUseCase,
    val searchUser: SearchUserUseCase,
    val toggleFollowStateForUser: ToggleFollowStateForUserUseCase,
    val logout: LogoutUseCase
) {
}