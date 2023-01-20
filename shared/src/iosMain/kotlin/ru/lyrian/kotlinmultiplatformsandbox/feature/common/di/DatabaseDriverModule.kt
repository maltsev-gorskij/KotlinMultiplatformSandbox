package ru.lyrian.kotlinmultiplatformsandbox.feature.common.di

import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache.DatabaseDriverFactory

internal actual class DatabaseDriverModule {
    actual fun create(): Module = module {
        single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    }
}