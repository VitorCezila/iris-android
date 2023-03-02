package com.ghn.iris.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghn.iris.presentation.ui.theme.*

@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandartBottomNavItem(
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = White,
    unselectedColor: Color = LightGray,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null && alertCount < 0) {
        throw IllegalArgumentException("Alert count can't be negative")
    }
    BottomNavigationItem(
        selected = selected,
        onClick = {
            onClick()
        },
        modifier = Modifier,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceSmall)
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                    if (alertCount != null) {
                        val alertText = if (alertCount > 99) {
                            "99+"
                        } else {
                            alertCount.toString()
                        }
                        Text(
                            text = alertText,
                            color = SocialPink,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .offset(10.dp)
                                .size(15.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    )
}