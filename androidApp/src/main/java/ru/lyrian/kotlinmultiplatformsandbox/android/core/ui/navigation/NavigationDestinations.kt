package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation

object NavigationDestinations {
   object HomeNavGraph {
      const val LAUNCHES = "launchesList"
      const val FAVORITES = "launchesFavorites"
      const val PROFILE = "profile"
   }

   object DetailsNavGraph {
      const val DETAILS = "launchDetails"

      const val LAUNCH_ID_ARG = "launchId"
      const val LAUNCH_TITLE_ARG = "launchTitle"
   }
}
