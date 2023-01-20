package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabase
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.api.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.LaunchesListDatabase
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.mappers.RocketLaunchMapper
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.domain.GetLaunchesListUseCase

internal val launchesListModule = module {
    single<AppDatabase> { AppDatabase(get()) }
    single<AppDatabaseQueries> { get<AppDatabase>().appDatabaseQueries }
    singleOf(::RocketLaunchMapper)
    singleOf(::LaunchesListApi)
    singleOf(::LaunchesListDatabase)
    singleOf(::GetLaunchesListUseCase)
}
