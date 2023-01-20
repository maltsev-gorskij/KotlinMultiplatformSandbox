package ru.lyrian.kotlinmultiplatformsandbox.di

import ru.lyrian.kotlinmultiplatformsandbox.feature.common.di.DatabaseDriverModule

fun commonModules() = listOf(
    DatabaseDriverModule().create(),
)
