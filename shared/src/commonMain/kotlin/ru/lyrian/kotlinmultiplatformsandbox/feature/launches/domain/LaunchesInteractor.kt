package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache.AppDatabaseRepository
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.api.LaunchesListApi

class LaunchesInteractor: KoinComponent {
    private val appDatabaseRepository: AppDatabaseRepository by inject()
    private val launchesListApi: LaunchesListApi by inject()

    @Throws(Exception::class)
    suspend fun getAllLaunches(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = appDatabaseRepository.getAllLaunches()

        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            launchesListApi.getAllLaunches().also {
                appDatabaseRepository.clearDatabase()
                appDatabaseRepository.createLaunches(it)
            }
        }
    }
}
