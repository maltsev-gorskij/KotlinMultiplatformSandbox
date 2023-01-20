package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navGraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.screens.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = NavGraphsDestinations.ROOT,
        startDestination = NavGraphsDestinations.HOME
    ) {
        composable(route = NavGraphsDestinations.HOME) {
            HomeScreen()
        }
    }
}
