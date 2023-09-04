package com.ghn.iris.feature_post.presentation.post_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.core.presentation.components.Post
import com.ghn.iris.core.presentation.components.SendTextField
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.core.presentation.ui.theme.DarkBlack
import com.ghn.iris.core.presentation.ui.theme.DarkGray
import com.ghn.iris.core.presentation.ui.theme.SpaceLarge
import com.ghn.iris.core.presentation.ui.theme.White
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.presentation.util.asString
import com.ghn.iris.core.presentation.util.showKeyboard
import com.ghn.iris.core.util.Screen
import com.ghn.iris.core.util.sendSharePostIntent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: PostDetailViewModel = hiltViewModel(),
    shouldShowKeyboard: Boolean = false
) {

    val state = viewModel.state.value
    val commentTextFieldState = viewModel.commentTextFieldState.value

    val focusRequester = remember {
        FocusRequester()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        if (shouldShowKeyboard) {
            context.showKeyboard()
            focusRequester.requestFocus()
        }
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                else -> {}
            }
        }

    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = stringResource(id = R.string.post),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            showBackArrow = true
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(DarkBlack)
                ) {
                    if (state.isLoadingPost) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    state.post?.let { post ->
                        Post(
                            post = post,
                            onLikeClicked = {
                                viewModel.onEvent(PostDetailEvent.LikePost)
                            },
                            onCommentClicked = {
                                context.showKeyboard()
                                focusRequester.requestFocus()
                            },
                            onShareClicked = {
                                context.sendSharePostIntent(post.id)
                            },
                            onUserClicked = {
                                onNavigate(Screen.ProfileScreen.route + "?userId=${post.userId}")
                            }
                        )

                        Divider(color = DarkGray, thickness = 3.dp)
                        Spacer(modifier = Modifier.height(SpaceLarge))

                        Text(
                            text = "COMMENTS (${post.commentCount})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                        )

                    }
                    if (state.isLoadingComments && !state.isLoadingPost) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

            }
            items(state.comments) { comment ->
                Comment(
                    comment = comment,
                    onLikeClick = {
                        viewModel.onEvent(PostDetailEvent.LikeComment(comment.id))
                    }
                )
            }
        }
        SendTextField(
            state = viewModel.commentTextFieldState.value,
            onValueChange = {
                viewModel.onEvent(PostDetailEvent.EnteredComment(it))
            },
            onSend = {
                viewModel.onEvent(PostDetailEvent.Comment)
            },
            hint = stringResource(id = R.string.enter_a_comment),
            isLoading = viewModel.commentState.value.isLoading,
            focusRequester = focusRequester,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}