package com.ghn.iris.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ghn.iris.presentation.ui.theme.Black
import com.ghn.iris.R
import com.ghn.iris.domain.BottomNavItem
import com.ghn.iris.presentation.util.Screen
import timber.log.Timber

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
        modifier = modifier
    ) {
        content()
    }
}