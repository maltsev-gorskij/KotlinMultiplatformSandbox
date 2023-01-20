package ru.lyrian.kotlinmultiplatformsandbox.domain.launches

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.LaunchesRepository

class LaunchesInteractor: KoinComponent {
    private val launchesRepository: LaunchesRepository by inject()
    private val launchesListApi: LaunchesListApi by inject()

    @Throws(Exception::class)
    suspend fun getAllLaunches(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = launchesRepository.getAllLaunches()

        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            launchesListApi.getAllLaunches().also {
                launchesRepository.clearDatabase()
                launchesRepository.createLaunches(it)
            }
        }
    }
}
