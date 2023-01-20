package ru.lyrian.kotlinmultiplatformsandbox.domain.launches

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches.LaunchesRepository

class LaunchesInteractor: KoinComponent {
    private val launchesRepository: LaunchesRepository by inject()

    @Throws(Exception::class)
    suspend fun getAllLaunches(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = launchesRepository.getAllCachedLaunches()

        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            launchesRepository.getUpToDateLaunches().also {
                launchesRepository.clearDatabase()
                launchesRepository.createLaunches(it)
            }
        }
    }
}
