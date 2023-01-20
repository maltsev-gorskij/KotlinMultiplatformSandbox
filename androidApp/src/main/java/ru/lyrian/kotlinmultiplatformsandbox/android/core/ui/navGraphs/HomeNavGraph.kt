package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navGraphs

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
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.bottomNavigation.BottomNavItems
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui.LaunchesListScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavGraphsDestinations.HOME,
        startDestination = BottomNavItems.Launches.route
    ) {
        composable(NavigationDestinations.HomeNavGraph.LAUNCHES) {
            LaunchesListScreen(
                onLaunchClicked = {
                    navController.navigate(NavigationDestinations.DetailsNavGraph.DETAILS)
                }
            )
        }
        composable(NavigationDestinations.HomeNavGraph.FAVORITES) {
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
        detailsNavGraph()
    }
}
