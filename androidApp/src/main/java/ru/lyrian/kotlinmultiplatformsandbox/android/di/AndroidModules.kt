package ru.lyrian.kotlinmultiplatformsandbox.android.di

import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.di.launchDetailsModule
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.di.launchesListModule

fun androidModules() = listOf(
    launchesListModule,
    launchDetailsModule
)
