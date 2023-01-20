package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.api.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.LaunchesListDatabase
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.RocketLaunch

class GetLaunchesListUseCase: KoinComponent {
    private val launchesListDatabase: LaunchesListDatabase by inject()
    private val launchesListApi: LaunchesListApi by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = launchesListDatabase.getAllLaunches()

        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            launchesListApi.getAllLaunches().also {
                launchesListDatabase.clearDatabase()
                launchesListDatabase.createLaunches(it)
            }
        }
    }
}
