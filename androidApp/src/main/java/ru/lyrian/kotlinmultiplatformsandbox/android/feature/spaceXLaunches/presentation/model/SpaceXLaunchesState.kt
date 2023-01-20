package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.model

import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.RocketLaunch

data class SpaceXLaunchesState(
    val launches: List<RocketLaunch> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
