package ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.di

import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabase
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.RocketLaunchApiMapper
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.SpaceXApiClientProvider

internal val dataSourceModule = module {
    single { SpaceXApiClientProvider(get()) }
    single { LaunchesListApi(get(), get()) }
    single { RocketLaunchApiMapper() }

    single { AppDatabase(get()) }
    single { get<AppDatabase>().appDatabaseQueries }
}
