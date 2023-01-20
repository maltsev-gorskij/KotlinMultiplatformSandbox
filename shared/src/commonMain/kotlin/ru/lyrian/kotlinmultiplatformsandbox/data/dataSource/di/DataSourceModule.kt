package ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.di

import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabase
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.RocketLaunchApiMapper

internal val dataSourceModule = module {
    single { LaunchesListApi(get(), get()) }
    single { RocketLaunchApiMapper() }

    single { AppDatabase(get()) }
    single { get<AppDatabase>().appDatabaseQueries }
}
