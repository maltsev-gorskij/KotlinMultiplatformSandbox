package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.api.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.api.RocketLaunchApiMapper
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.LaunchesInteractor

internal val launchesListModule = module {
    singleOf(::RocketLaunchApiMapper)
    singleOf(::LaunchesListApi)
    singleOf(::LaunchesInteractor)
}
