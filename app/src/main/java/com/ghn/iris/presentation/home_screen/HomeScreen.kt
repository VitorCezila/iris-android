package com.ghn.iris.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ghn.iris.domain.Post
import com.ghn.iris.presentation.components.Post
import com.ghn.iris.presentation.ui.theme.SpaceLarge

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Post(
                mockPost
            )
        }
    }
}

private val mockPost = Post(
    id = "0",
    userId = "0",
    username = "cezila",
    profilePictureUrl = "",
    content = "Meu primeiro Iris",
    imageUrl = "",
    formattedTime = "1 hour ago",
    likeCount = 10,
    commentCount = 1,
    sharesCount = 0,
    isLiked = true,
    isSaved = false,
    isOwnPost = true
)