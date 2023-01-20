package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navGraphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations

fun NavGraphBuilder.detailsNavGraph() {
    navigation(
        route = NavGraphsDestinations.DETAILS,
        startDestination = NavigationDestinations.DetailsNavGraph.DETAILS
    ) {
        composable(NavigationDestinations.DetailsNavGraph.DETAILS) {
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
