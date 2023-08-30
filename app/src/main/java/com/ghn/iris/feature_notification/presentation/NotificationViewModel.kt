package com.ghn.iris.feature_notification.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ghn.iris.feature_notification.domain.use_case.GetNotificationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotifications: GetNotificationsUseCase
): ViewModel() {

    val notifications = getNotifications.invoke().cachedIn(viewModelScope)
    private val _state = mutableStateOf(NotificationState())
    val state: State<NotificationState> = _state

}