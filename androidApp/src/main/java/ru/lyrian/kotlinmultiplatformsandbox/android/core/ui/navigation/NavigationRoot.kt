package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui.LaunchesListScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationDestinations.LAUNCHES_LIST) {
        composable(NavigationDestinations.LAUNCHES_LIST) { LaunchesListScreen(navController) }
        composable(NavigationDestinations.LAUNCHES_FAVORITES) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Favorites placeholder"
                    )
                }
            }
        }
        composable(NavigationDestinations.PROFILE) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Profile placeholder"
                    )
                }
            }
        }
        composable(NavigationDestinations.LAUNCHES_DETAILS) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Details placeholder"
                    )
                }
            }
        }
    }
}
