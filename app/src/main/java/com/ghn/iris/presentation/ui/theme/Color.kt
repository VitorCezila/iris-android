package com.ghn.iris.presentation.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//Primarys
val SocialPink = Color(0xFFF62E8E)
val SocialBlue = Color(0xFF2E8AF6)
val SocialWhite = Color(0xFFECEBED)
val DarkBlack = Color(0xFF181A1C) // Background
val Black = Color(0xFF000000) // Navigation Feed
val White = Color(0xFFFFFFFF)

// Gradient
val GradientLight = Color(0xFFF62E8E)
val GradientDark = Color(0xFFAC1AF0)
val GradientBrush = Brush.linearGradient(
    colors = listOf(GradientLight, GradientDark),
    start = Offset.Zero,
    end = Offset(100f, 0f)
)

// Secondary
val DarkGray = Color(0xFF323436) // Divider
val LightGray = Color(0xFF727477) // Used to texts

val Transparent = Color(0x00000000)