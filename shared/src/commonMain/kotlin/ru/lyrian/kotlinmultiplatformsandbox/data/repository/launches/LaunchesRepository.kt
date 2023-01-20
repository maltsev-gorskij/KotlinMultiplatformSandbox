package ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches

import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.RocketLaunch

internal class LaunchesRepository(
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
