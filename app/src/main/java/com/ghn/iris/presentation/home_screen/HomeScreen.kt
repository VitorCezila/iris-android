package com.ghn.iris.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ghn.iris.domain.Post
import com.ghn.iris.presentation.components.Post
import com.ghn.iris.presentation.ui.theme.DarkGray

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(6) {
                Post(mockPost)
                Divider(color = DarkGray, thickness = 3.dp)
            }
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