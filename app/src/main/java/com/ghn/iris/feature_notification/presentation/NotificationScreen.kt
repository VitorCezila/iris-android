package com.ghn.iris.feature_notification.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.Notification
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.feature_notification.presentation.components.NotificationItem
import com.ghn.iris.core.presentation.ui.theme.DarkGray
import com.ghn.iris.core.presentation.ui.theme.SpaceLarge
import com.ghn.iris.core.presentation.ui.theme.SpaceMedium
import com.ghn.iris.core.presentation.ui.theme.White

@Composable
fun NotificationScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: NotificationViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val notifications = viewModel.notifications.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StandardToolbar(
                onNavigateUp = onNavigateUp,
                title = {
                    Text(
                        text = stringResource(id = R.string.alerts),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body2,
                        color = White
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                showBackArrow = false
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(SpaceMedium)
            ) {
                items(notifications.itemCount) { i ->
                    val notification = notifications[i]
                    notification?.let {
                        NotificationItem(
                            notification = Notification(
                                userId = it.userId,
                                parentId = it.parentId,
                                username = it.username,
                                notificationType = it.notificationType,
                                formattedTime = it.formattedTime
                            )
                        )
                        Spacer(modifier = Modifier.height(SpaceLarge))
                        Divider(color = DarkGray, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(SpaceLarge))
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
