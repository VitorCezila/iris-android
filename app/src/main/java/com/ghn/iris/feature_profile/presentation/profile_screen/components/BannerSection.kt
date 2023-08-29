package com.ghn.iris.feature_profile.presentation.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.util.base64ToImageBitmap
import com.ghn.iris.core.util.toPx

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    profileBannerBase64: String? = null,
    iconSize: Dp = 35.dp,
) {

    val profileBannerBitMap = profileBannerBase64?.base64ToImageBitmap()

    BoxWithConstraints(
        modifier = modifier
    ) {
        if(profileBannerBitMap != null) {
            Image(
                bitmap = profileBannerBitMap.asImageBitmap(),
                contentDescription = stringResource(R.string.banner_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = constraints.maxHeight - iconSize.toPx() * 2f
                    )
                )
        )
    }
}