package ru.lyrian.kotlinmultiplatformsandbox.core.di.databaseDriver

import org.koin.core.module.Module

internal expect class DatabaseDriverModule() {
    fun create(): Module
}
