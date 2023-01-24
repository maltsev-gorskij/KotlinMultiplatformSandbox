package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model

sealed interface LaunchDetailsEvent {
    data class ShowErrorMessage(val message: String) : LaunchDetailsEvent
}
