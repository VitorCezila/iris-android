package com.ghn.iris.feature_profile.presentation.util

import com.ghn.iris.core.util.Error


sealed class EditProfileError : Error() {
    object FieldEmpty: EditProfileError()
}
