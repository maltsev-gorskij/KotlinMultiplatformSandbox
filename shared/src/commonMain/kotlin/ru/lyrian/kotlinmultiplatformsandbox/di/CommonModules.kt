package ru.lyrian.kotlinmultiplatformsandbox.di

import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.DatabaseDriverModule
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.apiClientModule

fun commonModules() = listOf(
    DatabaseDriverModule().create(),
    apiClientModule
)
