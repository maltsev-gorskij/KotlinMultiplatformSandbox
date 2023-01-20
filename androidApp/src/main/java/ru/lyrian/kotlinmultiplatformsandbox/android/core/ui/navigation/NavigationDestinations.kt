package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation

enum class NavigationDestinations(val route: String) {
   LAUNCHES_LIST(route = "launchesList"),
   LAUNCHES_FAVORITES(route = "launchesFavorites"),
   LAUNCHES_DETAILS(route = "launchDetails"),
   PROFILE(route = "profile")
}
