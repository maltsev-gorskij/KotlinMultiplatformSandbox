package ru.lyrian.kotlinmultiplatformsandbox.data.repository.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.LaunchesRepository
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.RocketLaunchMapper

internal val repositoryModule = module {
    singleOf(::LaunchesRepository)
    singleOf(::RocketLaunchMapper)
}
