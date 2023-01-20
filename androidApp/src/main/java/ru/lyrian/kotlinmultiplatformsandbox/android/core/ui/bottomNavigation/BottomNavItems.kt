package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.bottomNavigation

import androidx.annotation.DrawableRes
import ru.lyrian.kotlinmultiplatformsandbox.android.R

enum class BottomNavItems(val title: String, @DrawableRes val icon: Int, val route: String) {
    LAUNCHES_LIST(
        title = BottomNavTitles.LIST_TITLE,
        icon = R.drawable.ic_list,
        route = LAUNCHES_LIST.route
    ),
    LAUNCHES_FAVORITES(
        title = BottomNavTitles.FAVORITES_TITLE,
        icon = R.drawable.ic_favorites,
        route = LAUNCHES_FAVORITES.route
    ),
    PROFILE(
        title = BottomNavTitles.PROFILE_TITLE,
        icon = R.drawable.ic_profile,
        route = PROFILE.route
    )
}
