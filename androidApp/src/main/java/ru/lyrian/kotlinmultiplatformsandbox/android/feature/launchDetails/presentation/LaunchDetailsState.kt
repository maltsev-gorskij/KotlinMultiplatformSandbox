package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation

import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

data class LaunchDetailsState(
    val title: String,
    val launch: RocketLaunch? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
