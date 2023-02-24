package com.ghn.iris.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ghn.iris.R

val hkGrotesk = FontFamily(
    Font(R.font.hk_grotesk_regular, FontWeight.Normal),
    Font(R.font.hk_grotesk_medium, FontWeight.Medium),
    Font(R.font.hk_grotesk_bold, FontWeight.Bold),
    Font(R.font.hk_grotesk_semi_bold, FontWeight.SemiBold),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    h2 = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    h3 = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = hkGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
)