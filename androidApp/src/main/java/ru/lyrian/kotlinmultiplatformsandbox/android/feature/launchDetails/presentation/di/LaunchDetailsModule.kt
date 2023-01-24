package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.viewmodel.LaunchDetailsViewModel

val launchDetailsModule = module {
    viewModelOf(::LaunchDetailsViewModel)
}
