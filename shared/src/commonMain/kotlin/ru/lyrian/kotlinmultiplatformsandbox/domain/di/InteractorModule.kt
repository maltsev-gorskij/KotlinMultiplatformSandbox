package ru.lyrian.kotlinmultiplatformsandbox.domain.di

import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.LaunchesInteractor

internal val interactorModule = module {
    single { (LaunchesInteractor()) }
}
