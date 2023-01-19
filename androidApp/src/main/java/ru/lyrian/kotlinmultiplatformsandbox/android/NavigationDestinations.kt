package ru.lyrian.kotlinmultiplatformsandbox.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui.Launches

@Composable
fun NavigationHost() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@Destination(start = true)
@Composable
fun LaunchesListRoute(navigator: DestinationsNavigator) =
    Launches(navigator)

@Destination
@Composable
fun LaunchDetailsRoute(navigator: DestinationsNavigator) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Just a placeholder")
    }
}
