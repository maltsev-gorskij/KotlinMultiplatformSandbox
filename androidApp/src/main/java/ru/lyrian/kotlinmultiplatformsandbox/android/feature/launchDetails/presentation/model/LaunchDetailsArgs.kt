package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model

import androidx.lifecycle.SavedStateHandle
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationDestinations

class LaunchDetailsArgs(val launchId: String, val launchTitle: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                launchId = checkNotNull(
                    savedStateHandle[NavigationDestinations.DetailsNavGraph.LAUNCH_ID_ARG]
                ) as String,
                launchTitle = checkNotNull(
                    savedStateHandle[NavigationDestinations.DetailsNavGraph.LAUNCH_TITLE_ARG]
                ) as String
            )
}
