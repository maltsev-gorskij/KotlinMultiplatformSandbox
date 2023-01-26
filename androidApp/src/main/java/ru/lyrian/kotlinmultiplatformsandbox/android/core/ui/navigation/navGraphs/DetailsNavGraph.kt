package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.navGraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinations
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinationsArgs.Details.LAUNCH_ID_ARG
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.ui.LaunchDetailsScreen

fun NavGraphBuilder.detailsNavGraph(
    onNavigateBack: () -> Unit
) {
    navigation(
        route = NavGraphsDestinations.DETAILS,
        startDestination = NavDestinations.DetailsNavGraph.DETAILS
    ) {
        composable("${NavDestinations.DetailsNavGraph.DETAILS}/{$LAUNCH_ID_ARG}") {
            LaunchDetailsScreen(
                onNavigateBackClicked = onNavigateBack,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun NavController.navigateToLaunchDetails(launchId: String) {
    this.navigate("${NavDestinations.DetailsNavGraph.DETAILS}/$launchId")
}
