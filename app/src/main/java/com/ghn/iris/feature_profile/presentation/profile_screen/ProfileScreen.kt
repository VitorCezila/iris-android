package com.ghn.iris.feature_profile.presentation.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.ghn.iris.core.domain.models.User
import com.ghn.iris.core.presentation.components.Post
import com.ghn.iris.core.presentation.ui.theme.DarkGray
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.ghn.iris.core.util.Screen
import com.ghn.iris.feature_profile.presentation.profile_screen.components.BannerSection
import com.ghn.iris.feature_profile.presentation.profile_screen.components.ProfileHeaderSection

@Composable
fun ProfileScreen(
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                BannerSection(modifier = Modifier.aspectRatio(2.5f))
            }
            item {
                ProfileHeaderSection(
                    user = mockUser,
                    profilePictureSize = profilePictureSize,
                    onEditClick = {
                        onNavigate(Screen.EditProfileScreen.route)
                    }
                )
                Divider(color = DarkGray, thickness = 1.dp)
            }
            items(5) {
                Post(
                    post = mockPost,
                    onPostClicked = {
                        onNavigate(Screen.PostDetailScreen.route)
                    }
                )
                Divider(color = DarkGray, thickness = 3.dp)
            }
        }
    }
}

private val mockUser = User(
    userId = "0",
    username = "cezila",
    profileImageBase64 = "",
    description = "Writer by Profession. Artist by Passion!",
    followerCount = 2467,
    followingCount = 1589,
    postCount = 5
)

private val mockPost = com.ghn.iris.core.domain.models.Post(
    id = "0",
    userId = "0",
    username = "cezila",
    profilePictureBase64 = "",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a sem quam. Integer placerat efficitur mattis. Ut magna nunc, dictum rutrum augue ut, condimentum sollicitudin nisl. In est turpis, egestas in ex eu.",
    imageBase64 = "",
    formattedTime = "1 hour ago",
    likeCount = 10,
    commentCount = 3,
    sharesCount = 0,
    isLiked = false,
    isSaved = false,
    isOwnPost = true
)