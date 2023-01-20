package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.model

import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

data class LaunchesListState(
    val launches: List<RocketLaunch> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
