package ru.lyrian.kotlinmultiplatformsandbox.core.di.databaseDriver

import com.squareup.sqldelight.db.SqlDriver

internal expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
