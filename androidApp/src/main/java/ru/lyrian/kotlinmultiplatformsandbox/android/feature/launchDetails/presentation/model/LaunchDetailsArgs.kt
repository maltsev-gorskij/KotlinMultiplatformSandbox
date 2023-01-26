package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model

import androidx.lifecycle.SavedStateHandle
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinationsArgs.Details.LAUNCH_ID_ARG

class LaunchDetailsArgs(val launchId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                launchId = checkNotNull(
                    savedStateHandle[LAUNCH_ID_ARG]
                ) as String
            )
}
