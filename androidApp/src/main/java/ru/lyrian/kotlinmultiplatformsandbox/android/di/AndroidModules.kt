package ru.lyrian.kotlinmultiplatformsandbox.android.di

import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.di.launchDetailsModule
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.di.launchesListModule

fun androidModules() = listOf(
    launchesListModule,
    launchDetailsModule
)
