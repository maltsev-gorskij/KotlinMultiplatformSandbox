package ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabase
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.api.SpaceXApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.cache.Database
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.mappers.RocketLaunchMapper
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.domain.SpaceXSDK

val spaceXSdkModule = module {
    single<AppDatabase> { AppDatabase(get()) }
    single<AppDatabaseQueries> { get<AppDatabase>().appDatabaseQueries }
    singleOf(::RocketLaunchMapper)
    singleOf(::SpaceXApi)
    singleOf(::Database)
    singleOf(::SpaceXSDK)
}
