package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.bottomNavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItems.Launches,
        BottomNavItems.Favorites,
        BottomNavItems.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var shouldShowBottomNavBar by rememberSaveable { (mutableStateOf(true)) }

    LaunchedEffect(key1 = currentRoute) {
        shouldShowBottomNavBar = items.any { it.route == currentRoute }
    }

    AnimatedVisibility(
        visible = shouldShowBottomNavBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavBarContent(
            items = items,
            currentRoute = currentRoute,
            navController = navController
        )
    }
}

@Composable
private fun BottomNavBarContent(
    items: List<BottomNavItems>,
    currentRoute: String?,
    navController: NavHostController,
) {
    BottomNavigation {
        items.forEach { item: BottomNavItems ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = false,
            )
        }
    }
}
