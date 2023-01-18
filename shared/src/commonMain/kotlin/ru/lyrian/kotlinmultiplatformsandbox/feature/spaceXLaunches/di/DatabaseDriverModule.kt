package ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.di

import org.koin.core.module.Module

internal expect class DatabaseDriverModule() {
    fun create(): Module
}
