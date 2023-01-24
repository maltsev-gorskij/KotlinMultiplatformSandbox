package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation

sealed interface LaunchDetailsEvent {
    data class ShowErrorMessage(val message: String) : LaunchDetailsEvent
}
