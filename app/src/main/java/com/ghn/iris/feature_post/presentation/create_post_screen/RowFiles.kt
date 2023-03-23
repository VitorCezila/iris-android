package com.ghn.iris.feature_post.presentation.create_post_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ghn.iris.core.presentation.components.SimpleCircleBorder
import com.ghn.iris.core.presentation.ui.theme.DarkGray
import com.ghn.iris.core.presentation.ui.theme.SpaceSmall

@Composable
fun RowFiles(
    modifier: Modifier = Modifier,
    showMenu: Boolean = false,
    onMenuClicked: () -> Unit = {}
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        SimpleCircleBorder(
            modifier = Modifier.size(36.dp)
        ) {
            IconButton(onClick = {
                onMenuClicked()
            }) {
                if (showMenu) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                } else {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
        
        Spacer(modifier = Modifier.width(SpaceSmall))

        if (showMenu) {
            Row(
                modifier = Modifier
                    .background(color = DarkGray, shape = CircleShape)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Image, contentDescription = "Image")
                }

                IconButton(onClick = { }) {
                    Icon(Icons.Filled.CameraAlt, contentDescription = "Camera")
                }

                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Gif, contentDescription = "Gif")
                }
            }
        }
    }
}