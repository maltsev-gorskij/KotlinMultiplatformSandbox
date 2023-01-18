package ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.api.SpaceXApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.cache.Database
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.cache.RocketLaunch

class SpaceXSDK: KoinComponent {
    private val database: Database by inject()
    private val spaceXApi: SpaceXApi by inject()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean = false): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()

        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            spaceXApi.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
