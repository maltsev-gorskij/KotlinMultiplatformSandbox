package ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache

import com.squareup.sqldelight.db.SqlDriver

internal expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}