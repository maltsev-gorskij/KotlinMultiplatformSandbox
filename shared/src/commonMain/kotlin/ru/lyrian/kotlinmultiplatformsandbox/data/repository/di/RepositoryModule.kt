package ru.lyrian.kotlinmultiplatformsandbox.data.repository.di

import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.LaunchesRepository
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.RocketLaunchDatabaseMapper

internal val repositoryModule = module {
    single { LaunchesRepository(get(), get()) }
    single { RocketLaunchDatabaseMapper() }
}
