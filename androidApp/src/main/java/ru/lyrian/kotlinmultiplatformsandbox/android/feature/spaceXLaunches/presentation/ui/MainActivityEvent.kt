package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui

sealed interface MainActivityEvent {
    data class ShowToast(val message: String): MainActivityEvent
}
