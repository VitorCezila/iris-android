package com.ghn.iris.presentation.notification_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghn.iris.R
import com.ghn.iris.domain.models.Notification
import com.ghn.iris.domain.util.NotificationAction
import com.ghn.iris.presentation.ui.theme.*
import com.ghn.iris.presentation.util.Screen
import com.ghn.iris.util.Constants.NotificationBodySize
import com.ghn.iris.util.Constants.NotificationIconSize

@Composable
fun NotificationItem(
    notification: Notification,
    onNavigate: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val iconResId: Int = getIconResId(notification.notificationType)
    val tintColor = getTintColor(notification.notificationType)

    val (fillerText, actionText) = getNotificationTexts(notification)

    val annotatedText = buildAnnotatedString {
        val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
        pushStringAnnotation(
            tag = "username",
            annotation = "username"
        )
        withStyle(boldStyle) {
            append(notification.username)
        }

        pop()

        append(" $fillerText ")

        pushStringAnnotation(
            tag = "parent",
            annotation = "parent"
        )
        withStyle(boldStyle) {
            append(actionText)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(NotificationBodySize)
                    .background(color = DarkGray, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = stringResource(R.string.notification_icon),
                    modifier = Modifier.size(NotificationIconSize),
                    colorFilter = ColorFilter.tint(tintColor),
                    alpha = 2f
                )
            }

            Spacer(modifier = Modifier.width(SpaceLarge))

            Column {
                ClickableText(
                    text = annotatedText,
                    style = TextStyle(
                        color = SocialWhite,
                        fontSize = 12.sp
                    ),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "username",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { annotation ->
                            onNavigate(
                                Screen.ProfileScreen.route + "?userId=${notification.userId}"
                            )
                        }
                        annotatedText.getStringAnnotations(
                            tag = "parent",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { annotation ->
                            onNavigate(
                                Screen.PostDetailScreen.route + "/${notification.parentId}"
                            )
                        }
                    }
                )
                Text(
                    text = notification.formattedTime,
                    style = MaterialTheme.typography.body2,
                    color = LightGray
                )
            }

        }

    }
}

fun getIconResId(notificationType: NotificationAction): Int {
    return when (notificationType) {
        is NotificationAction.LikedComment -> {
            R.drawable.ic_like
        }
        is NotificationAction.LikedPost -> {
            R.drawable.ic_like
        }
        is NotificationAction.CommentedOnPost -> {
            R.drawable.ic_comment
        }
        is NotificationAction.FollowedUser -> {
            R.drawable.ic_follow
        }
    }
}

fun getTintColor(activityType: NotificationAction): Color {
    return when (activityType) {
        is NotificationAction.LikedComment -> {
            SocialBlue
        }
        is NotificationAction.LikedPost -> {
            SocialBlue
        }
        is NotificationAction.CommentedOnPost -> {
            SocialPink
        }
        is NotificationAction.FollowedUser -> {
            SocialPink
        }
    }
}

@Composable
fun getNotificationTexts(notification: Notification): Pair<String, String> {
    val fillerText = when (notification.notificationType) {
        is NotificationAction.LikedPost ->
            stringResource(id = R.string.liked)
        is NotificationAction.CommentedOnPost ->
            stringResource(id = R.string.commented_on)
        is NotificationAction.FollowedUser ->
            stringResource(id = R.string.followed_you)
        is NotificationAction.LikedComment -> {
            stringResource(id = R.string.liked)
        }
    }
    val actionText = when (notification.notificationType) {
        is NotificationAction.LikedPost ->
            stringResource(id = R.string.your_post)
        is NotificationAction.CommentedOnPost ->
            stringResource(id = R.string.your_post)
        is NotificationAction.FollowedUser -> ""
        is NotificationAction.LikedComment -> {
            stringResource(id = R.string.your_comment)
        }
    }
    return Pair(fillerText, actionText)
}