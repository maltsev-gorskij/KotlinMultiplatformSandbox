package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model

import androidx.lifecycle.SavedStateHandle
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinationsArgs.Details.LAUNCH_ID_ARG
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.destinations.NavDestinationsArgs.Details.LAUNCH_TITLE_ARG

class LaunchDetailsArgs(val launchId: String, val launchTitle: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                launchId = checkNotNull(
                    savedStateHandle[LAUNCH_ID_ARG]
                ) as String,
                launchTitle = checkNotNull(
                    savedStateHandle[LAUNCH_TITLE_ARG]
                ) as String
            )
}
