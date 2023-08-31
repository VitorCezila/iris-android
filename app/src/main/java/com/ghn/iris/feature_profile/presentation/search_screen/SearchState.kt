package com.ghn.iris.feature_profile.presentation.search_screen

import com.ghn.iris.core.domain.models.UserItem

data class SearchState(
    val userItems: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
