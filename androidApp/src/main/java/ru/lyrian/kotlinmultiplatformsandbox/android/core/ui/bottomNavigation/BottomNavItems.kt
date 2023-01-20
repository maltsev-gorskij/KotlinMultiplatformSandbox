package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.bottomNavigation

import ru.lyrian.kotlinmultiplatformsandbox.android.R
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations

sealed class BottomNavItems(val title: String, val icon: Int, val route: String) {
    object Launches : BottomNavItems(
        title = BottomNavTitles.LIST_TITLE,
        icon = R.drawable.ic_list,
        route = NavigationDestinations.HomeNavGraph.LAUNCHES
    )
    object Favorites : BottomNavItems(
        title = BottomNavTitles.FAVORITES_TITLE,
        icon = R.drawable.ic_favorites,
        route = NavigationDestinations.HomeNavGraph.FAVORITES
    )
    object Profile : BottomNavItems(
        title = BottomNavTitles.PROFILE_TITLE,
        icon = R.drawable.ic_profile,
        route = NavigationDestinations.HomeNavGraph.PROFILE
    )
}
