package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.navGraphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.bottomNavigation.BottomNavItems
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinations
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui.LaunchesListScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavGraphsDestinations.HOME,
        startDestination = BottomNavItems.Launches.route
    ) {
        composable(NavDestinations.HomeNavGraph.LAUNCHES) {
            LaunchesListScreen(
                onLaunchClicked = { id, title ->
                    navController.navigateToLaunchDetails(id, title)
                }
            )
        }
        composable(NavDestinations.HomeNavGraph.FAVORITES) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Favorites placeholder"
                    )
                }
            }
        }
        composable(BottomNavItems.Profile.route) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Profile placeholder"
                    )
                }
            }
        }
        detailsNavGraph(
            onNavigateBack = { navController.popBackStack() }
        )
    }
}
