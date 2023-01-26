package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.bottomNavigation

import ru.lyrian.kotlinmultiplatformsandbox.android.R
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinations

sealed class BottomNavItems(val title: String, val icon: Int, val route: String) {
    object Launches : BottomNavItems(
        title = BottomNavTitles.LIST_TITLE,
        icon = R.drawable.ic_list,
        route = NavDestinations.HomeNavGraph.LAUNCHES
    )
    object Favorites : BottomNavItems(
        title = BottomNavTitles.FAVORITES_TITLE,
        icon = R.drawable.ic_favorites,
        route = NavDestinations.HomeNavGraph.FAVORITES
    )
    object Profile : BottomNavItems(
        title = BottomNavTitles.PROFILE_TITLE,
        icon = R.drawable.ic_profile,
        route = NavDestinations.HomeNavGraph.PROFILE
    )
}
