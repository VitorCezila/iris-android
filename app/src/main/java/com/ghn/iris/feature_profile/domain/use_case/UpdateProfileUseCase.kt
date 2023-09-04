package com.ghn.iris.feature_profile.domain.use_case

import android.net.Uri
import androidx.compose.ui.res.stringResource
import com.ghn.iris.R
import com.ghn.iris.core.domain.repository.ProfileRepository
import com.ghn.iris.core.domain.util.ValidationUtil
import com.ghn.iris.core.util.Constants
import com.ghn.iris.core.util.Resource
import com.ghn.iris.core.util.SimpleResource
import com.ghn.iris.core.util.UiText
import com.ghn.iris.feature_auth.domain.models.RegisterResult
import com.ghn.iris.feature_profile.domain.model.UpdateProfileData

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?,
        bannerImageUri: Uri?
    ): SimpleResource {
        val usernameError = ValidationUtil.validateUsername(updateProfileData.username)
        if(usernameError != null) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.username_input_too_short)
            )
        }

        if(updateProfileData.username.isBlank() || updateProfileData.bio.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_username_bio_empty)
            )
        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerImageUri
        )
    }

}