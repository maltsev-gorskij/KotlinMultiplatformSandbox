package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.viewmodel.SpaceXLaunchesViewModel

val androidSpaceXModule = module {
    viewModelOf(::SpaceXLaunchesViewModel)
}
