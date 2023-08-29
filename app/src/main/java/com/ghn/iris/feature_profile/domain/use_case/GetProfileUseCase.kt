package com.ghn.iris.feature_profile.domain.use_case

import com.ghn.iris.core.domain.repository.ProfileRepository
import com.ghn.iris.core.util.Resource
import com.ghn.iris.feature_profile.domain.model.Profile

class GetProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String): Resource<Profile> {
        return repository.getProfile(userId)
    }

}
