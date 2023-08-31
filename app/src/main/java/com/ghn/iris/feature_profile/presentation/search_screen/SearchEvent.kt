package com.ghn.iris.feature_profile.presentation.search_screen

sealed class SearchEvent {
    data class Query(val query: String): SearchEvent()
    data class ToggleFollowState(val userId: String): SearchEvent()
}
