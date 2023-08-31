package com.ghn.iris.feature_profile.domain.use_case

import com.ghn.iris.core.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke() {
        repository.logout()
    }

}
