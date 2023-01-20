package ru.lyrian.kotlinmultiplatformsandbox.core.di

import ru.lyrian.kotlinmultiplatformsandbox.core.di.databaseDriver.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.core.di.httpClient.apiClientModule
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.di.dataSourceModule
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.di.repositoryModule
import ru.lyrian.kotlinmultiplatformsandbox.domain.di.interactorModule

fun sharedModules() = listOf(
    DatabaseDriverModule().create(),
    apiClientModule,
    dataSourceModule,
    repositoryModule,
    interactorModule
)
