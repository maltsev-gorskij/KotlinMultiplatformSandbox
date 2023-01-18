package ru.lyrian.kotlinmultiplatformsandbox.di

import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.di.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.di.spaceXSdkModule

fun commonModules() = listOf(
    DatabaseDriverModule().create(),
    spaceXSdkModule
)
