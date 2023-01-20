package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui

sealed interface LaunchesListEvent {
    data class ShowToast(val message: String): LaunchesListEvent
}
