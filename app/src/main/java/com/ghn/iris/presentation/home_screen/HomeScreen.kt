package com.ghn.iris.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.domain.models.Post
import com.ghn.iris.presentation.components.Post
import com.ghn.iris.presentation.components.SimpleCircleBorder
import com.ghn.iris.presentation.components.StandardToolbar
import com.ghn.iris.presentation.ui.theme.DarkGray
import com.ghn.iris.presentation.ui.theme.White
import com.ghn.iris.presentation.util.Screen

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {}
) {
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
                items(6) {
                    Post(
                        mockPost,
                        onPostClicked = {
                            onNavigate(Screen.PostDetailScreen.route)
                        }
                    )
                    Divider(color = DarkGray, thickness = 3.dp)
                }
            }
        }
    }
}

private val mockPost = Post(
    id = "0",
    userId = "0",
    username = "cezila",
    profilePictureUrl = "",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a sem quam. Integer placerat efficitur mattis. Ut magna nunc, dictum rutrum augue ut, condimentum sollicitudin nisl. In est turpis, egestas in ex eu.",
    imageUrl = "",
    formattedTime = "1 hour ago",
    likeCount = 10,
    commentCount = 3,
    sharesCount = 0,
    isLiked = false,
    isSaved = false,
    isOwnPost = true
)