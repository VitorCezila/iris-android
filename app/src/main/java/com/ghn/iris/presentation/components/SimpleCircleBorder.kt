package com.ghn.iris.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ghn.iris.presentation.ui.theme.DarkGray

@Composable
fun SimpleCircleBorder(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .border(width = 0.5.dp, color = DarkGray, shape = CircleShape)
    ) {
        content()
    }
}