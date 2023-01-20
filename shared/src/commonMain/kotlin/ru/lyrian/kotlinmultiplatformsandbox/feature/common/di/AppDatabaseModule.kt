package ru.lyrian.kotlinmultiplatformsandbox.feature.common.di

import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabase
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache.AppDatabaseRepository

val appDatabaseModule = module {
    single { AppDatabase(get()) }
    single { AppDatabaseRepository(get(), get()) }
    single { get<AppDatabase>().appDatabaseQueries }
}
