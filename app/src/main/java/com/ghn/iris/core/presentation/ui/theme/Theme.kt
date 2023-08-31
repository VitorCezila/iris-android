package com.ghn.iris.core.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = SocialPink,
    background = DarkBlack,
    onBackground = White,
    onPrimary = White,
    onSecondary = LightGray
)

@Composable
fun IrisTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}