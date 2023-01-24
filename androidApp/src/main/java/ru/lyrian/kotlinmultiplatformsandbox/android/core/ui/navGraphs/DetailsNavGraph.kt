package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navGraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations.DetailsNavGraph.LAUNCH_ID_ARG
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations.DetailsNavGraph.LAUNCH_TITLE_ARG
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.ui.LaunchDetailsScreen
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.LaunchDetailsScreen

fun NavGraphBuilder.detailsNavGraph(
    onNavigateBack: () -> Unit
) {
    navigation(
        route = NavGraphsDestinations.DETAILS,
        startDestination = NavigationDestinations.DetailsNavGraph.DETAILS
    ) {
        composable("${NavigationDestinations.DetailsNavGraph.DETAILS}/{$LAUNCH_ID_ARG}/{$LAUNCH_TITLE_ARG}") {
            LaunchDetailsScreen(
                onNavigateBackClicked = onNavigateBack,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun NavController.navigateToLaunchDetails(launchId: String, launchTitle: String) {
    this.navigate("${NavigationDestinations.DetailsNavGraph.DETAILS}/$launchId/$launchTitle")
}
