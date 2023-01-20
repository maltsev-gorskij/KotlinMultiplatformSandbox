package ru.lyrian.kotlinmultiplatformsandbox.core.di.databaseDriver

import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual class DatabaseDriverModule {
    actual fun create(): Module = module {
        single<SqlDriver> { DatabaseDriverFactory(get()).createDriver() }
    }
}
