package ru.lyrian.kotlinmultiplatformsandbox.core.di

import ru.lyrian.kotlinmultiplatformsandbox.core.di.databaseDriver.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.core.di.httpClient.apiClientModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.di.launchesModule

internal fun sharedModules() = listOf(
    DatabaseDriverModule().create(),
    apiClientModule,
    launchesModule
)
