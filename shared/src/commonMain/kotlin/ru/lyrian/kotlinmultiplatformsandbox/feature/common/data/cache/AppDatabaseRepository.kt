package ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache

import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class AppDatabaseRepository(
    private val appDatabaseQueries: AppDatabaseQueries,
    private val rocketLaunchDatabaseMapper: RocketLaunchDatabaseMapper
) {
    internal fun clearDatabase() {
        appDatabaseQueries.removeAllLaunches()
    }

    internal fun getAllLaunches(): List<RocketLaunch> {
        return appDatabaseQueries
            .selectAllLaunchesInfo()
            .executeAsList()
            .map {
                rocketLaunchDatabaseMapper(it)
            }
    }

    internal fun createLaunches(launches: List<RocketLaunch>) {
        appDatabaseQueries.transaction {
            launches.forEach { launch ->
                insertLaunch(launch)
            }
        }
    }

    private fun insertLaunch(launch: RocketLaunch) {
        appDatabaseQueries.insertLaunch(
            flightNumber = launch.flightNumber,
            missionName = launch.missionName,
            launchYear = launch.launchYear,
            details = launch.details,
            launchSuccess = launch.launchSuccess ?: false,
            launchDateUTC = launch.launchDateUTC,
            articleUrl = launch.articleUrl
        )
    }
}
