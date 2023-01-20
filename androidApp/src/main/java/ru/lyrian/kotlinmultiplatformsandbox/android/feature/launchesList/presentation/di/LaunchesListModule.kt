package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.viewmodel.LaunchesListViewModel

val launchesListModule = module {
    viewModelOf(::LaunchesListViewModel)
}
