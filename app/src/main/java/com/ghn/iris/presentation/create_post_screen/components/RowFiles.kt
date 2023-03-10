package com.ghn.iris.presentation.create_post_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ghn.iris.presentation.ui.theme.DarkGray

@Composable
fun RowFiles(
    modifier: Modifier = Modifier,
    showMenu: Boolean = false,
    onMenuClicked: () -> Unit = {}
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
        if (showMenu) {
            Row(
                modifier = Modifier
                    .background(color = DarkGray, shape = CircleShape)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.Image, contentDescription = "Image")
                }

                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.CameraAlt, contentDescription = "Camera")
                }

                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.Gif, contentDescription = "Gif")
                }
            }
        }
    }
}