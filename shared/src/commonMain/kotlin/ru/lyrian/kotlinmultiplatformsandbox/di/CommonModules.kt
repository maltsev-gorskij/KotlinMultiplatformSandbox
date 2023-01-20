package ru.lyrian.kotlinmultiplatformsandbox.di

import ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache.di.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache.di.appDatabaseModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.apiClientModule

fun commonModules() = listOf(
    DatabaseDriverModule().create(),
    apiClientModule,
    appDatabaseModule
)
