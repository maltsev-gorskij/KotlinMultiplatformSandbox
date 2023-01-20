package ru.lyrian.kotlinmultiplatformsandbox.di

import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.apiClientModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.appDatabaseModule

fun commonModules() = listOf(
    DatabaseDriverModule().create(),
    apiClientModule,
    appDatabaseModule
)
