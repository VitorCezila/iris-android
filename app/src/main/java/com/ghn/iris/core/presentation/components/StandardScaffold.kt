package com.ghn.iris.core.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.Black
import com.ghn.iris.core.presentation.ui.theme.GradientBrush
import com.ghn.iris.core.presentation.ui.theme.Transparent
import com.ghn.iris.core.domain.models.BottomNavItem
import com.ghn.iris.core.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    state: ScaffoldState,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.ic_home),
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.SearchScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.ic_search),
            contentDescription = "Search"
        ),
        BottomNavItem(route = "-"),
        BottomNavItem(
            route = Screen.NotificationScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.ic_notifications),
            contentDescription = "Notifications"
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.ic_profile),
            contentDescription = "Profile"
        ),
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Black,
                    cutoutShape = CircleShape,
                    elevation = 5.dp
                ) {
                    BottomNavigation(
                        backgroundColor = Black
                    ) {
                        bottomNavItems.forEach {
                            StandartBottomNavItem(
                                icon = it.icon,
                                contentDescription = it.contentDescription,
                                selected = navController.currentDestination?.route?.startsWith(it.route) == true,
                                alertCount = it.alertCount,
                                enabled = it.icon != null
                            ) {
                                if (navController.currentDestination?.route != it.route) {
                                    navController.navigate(it.route)
                                }
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = state,
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    backgroundColor = Transparent,
                    onClick = onFabClick,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                    modifier = Modifier.background(GradientBrush, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.make_post)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        modifier = modifier
    ) {
        content()
    }
}