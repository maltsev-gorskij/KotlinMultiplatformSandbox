package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.model

import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.RocketLaunch

data class LaunchesListState(
    val launches: List<RocketLaunch> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
