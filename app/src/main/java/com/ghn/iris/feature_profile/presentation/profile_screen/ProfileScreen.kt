package com.ghn.iris.feature_profile.presentation.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.User
import com.ghn.iris.core.presentation.components.Post
import com.ghn.iris.core.presentation.ui.theme.DarkGray
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.ghn.iris.core.presentation.ui.theme.SpaceMedium
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.presentation.util.asString
import com.ghn.iris.core.util.Screen
import com.ghn.iris.core.util.sendSharePostIntent
import com.ghn.iris.core.util.toPx
import com.ghn.iris.feature_profile.presentation.profile_screen.components.BannerSection
import com.ghn.iris.feature_profile.presentation.profile_screen.components.ProfileHeaderSection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel(),
    userId: String? = null,
    onLogout: () -> Unit = {},
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    val context = LocalContext.current
    val pagingState = viewModel.pagingState.value
    val lazyListState = rememberLazyListState()
    val toolbarHeightCollapsed = 75.dp
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + profilePictureSize
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }

    val state = viewModel.state.value

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val shouldNotScroll = delta > 0f && lazyListState.firstVisibleItemIndex != 0 ||
                        viewModel.pagingState.value.items.isEmpty()
                if (shouldNotScroll) {
                    return Offset.Zero
                }
                val newOffset = viewModel.toolbarState.value.toolbarOffsetY + delta
                viewModel.setToolbarOffsetY(
                    newOffset.coerceIn(
                        minimumValue = -maxOffset.toPx(),
                        maximumValue = 0f
                    )
                )
                viewModel.setExpandedRatio((viewModel.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                return Offset.Zero
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getProfile(userId)
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }

            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            item {
                state.profile?.let { profile ->
                    BannerSection(
                        modifier = Modifier.aspectRatio(2.5f),
                        profileBannerBase64 = profile.profileBannerBase64
                    )
                    ProfileHeaderSection(
                        user = User(
                            userId = profile.userId,
                            profileImageBase64 = profile.profileImageBase64,
                            username = profile.username,
                            description = profile.bio,
                            followerCount = profile.followerCount,
                            followingCount = profile.followingCount,
                            postCount = profile.postCount
                        ),
                        isFollowing = profile.isFollowing,
                        isOwnProfile = profile.isOwnProfile,
                        onLogoutClick = {
                            viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                        },
                        onEditClick = {
                            onNavigate(Screen.EditProfileScreen.route + "/${profile.userId}")
                        },
                        onMessageClick = {
                            Timber.d("Profile user id is ${profile.userId}")
                            GlobalScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "This feature is coming soon")
                            }
//                            val encodedProfilePictureUrl = Base64.encodeToString(profile.profileImageBase64.encodeToByteArray(), 0)
//                            onNavigate(Screen.MessagesScreen.route + "/${profile.userId}/${profile.username}/${encodedProfilePictureUrl}")
                        },
                        onFollowClick = {
                            viewModel.onEvent(ProfileEvent.ToggleFollowState(profile.userId))
                        },
                        profilePictureSize = profilePictureSize
                    )
                    Divider(color = DarkGray, thickness = 1.dp)
                }
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (pagingState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }

            }
            items(pagingState.items.size) {i ->
                val post = pagingState.items[i]
                if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                    viewModel.loadNextPosts()
                }
                Post(
                    post = post,
                    onPostClicked = {
                        onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                    },
                    onCommentClicked = {
                        onNavigate(Screen.PostDetailScreen.route + "/${post.id}?shouldShowKeyboard=true")
                    },
                    onLikeClicked = {
                        viewModel.onEvent(ProfileEvent.LikePost(post.id))
                    },
                    onShareClicked = {
                        context.sendSharePostIntent(post.id)
                    },
                    onDeleteClick = {
                        viewModel.onEvent(ProfileEvent.DeletePost(post))
                    }
                )
                Divider(color = DarkGray, thickness = 3.dp)
            }
        }
        if (state.isLogoutDialogVisible) {
            Dialog(onDismissRequest = {
                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
            }) {
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(SpaceMedium)
                ) {
                    Text(text = stringResource(id = R.string.do_you_want_to_logout))
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = stringResource(id = R.string.no).uppercase(),
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                            }
                        )
                        Spacer(modifier = Modifier.width(SpaceMedium))
                        Text(
                            text = stringResource(id = R.string.yes).uppercase(),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ProfileEvent.Logout)
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                onLogout()
                            }
                        )
                    }
                }
            }
        }
    }
}