package com.ghn.iris.presentation.notification_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.domain.models.Notification
import com.ghn.iris.domain.util.NotificationAction
import com.ghn.iris.presentation.components.StandardToolbar
import com.ghn.iris.presentation.notification_screen.components.NotificationItem
import com.ghn.iris.presentation.ui.theme.DarkGray
import com.ghn.iris.presentation.ui.theme.SpaceLarge
import com.ghn.iris.presentation.ui.theme.SpaceMedium
import com.ghn.iris.presentation.ui.theme.White

@Composable
fun NotificationScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: NotificationViewModel = hiltViewModel()
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
            items(5) {

                NotificationItem(
                    notification = Notification(
                        userId = "",
                        parentId = "",
                        "cezila",
                        NotificationAction.CommentedOnPost,
                        "30m ago"
                    )
                )

                Spacer(modifier = Modifier.height(SpaceLarge))
                Divider(color = DarkGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
        }
    }
}
