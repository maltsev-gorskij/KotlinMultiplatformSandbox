package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model

import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

data class LaunchDetailsState(
    val launch: RocketLaunch? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
