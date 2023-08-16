package com.ghn.iris.feature_post.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.ghn.iris.R
import com.ghn.iris.core.presentation.components.Post
import com.ghn.iris.core.presentation.components.SimpleCircleBorder
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.core.presentation.ui.theme.SpaceLarge
import com.ghn.iris.core.presentation.ui.theme.White
import com.ghn.iris.core.util.Screen
import com.ghn.iris.core.util.sendSharePostIntent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    imageLoader: ImageLoader,
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {

            }
        }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    "Good Morning, cezila",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    color = White
                )
            },
            navActions = {
                SimpleCircleBorder(
                    modifier = Modifier.size(36.dp)
                ) {
                    IconButton(onClick = {
                        onNavigate(Screen.MessagesScreen.route)
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_mail),
                            contentDescription = stringResource(R.string.message)
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(pagingState.items.size) { i ->
                    val post = pagingState.items[i]
                    if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                        viewModel.loadNextPosts()
                    }
                    Post(
                        post = post,
                        onPostClicked = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                        },
                        onLikeClicked = {
                            viewModel.onEvent(HomeEvent.LikedPost(post.id))
                        },
                        onCommentClicked = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post.id}?shouldShowKeyboard=true")
                        },
                        onShareClicked = {
                            context.sendSharePostIntent(post.id)
                        },
                        onUserClicked = {
                            onNavigate(Screen.ProfileScreen.route + "?userId=${post.userId}")
                        }
                    ) {
                        viewModel.onEvent(HomeEvent.DeletePost(post))
                    }
                    if (i < pagingState.items.size - 1) {
                        Spacer(modifier = Modifier.height(SpaceLarge))
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
            if (pagingState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}