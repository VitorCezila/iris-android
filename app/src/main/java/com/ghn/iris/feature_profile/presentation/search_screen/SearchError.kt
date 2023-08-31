package com.ghn.iris.feature_profile.presentation.search_screen

import com.ghn.iris.core.util.Error
import com.ghn.iris.core.util.UiText

data class SearchError(
    val message: UiText
): Error()
