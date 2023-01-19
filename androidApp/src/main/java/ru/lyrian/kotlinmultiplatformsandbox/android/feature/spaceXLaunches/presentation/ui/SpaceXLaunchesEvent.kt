package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui

sealed interface SpaceXLaunchesEvent {
    data class ShowToast(val message: String): SpaceXLaunchesEvent
}
