package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.repository

import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.dataSource.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.dataSource.RocketLaunchResponse
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class LaunchesRepository(
    private val launchesListApi: LaunchesListApi,
    private val appDatabaseQueries: AppDatabaseQueries,
    private val rocketLaunchMapper: RocketLaunchMapper
) {
    internal suspend fun getUpToDateLaunches(): List<RocketLaunch> {
        val launches: List<RocketLaunchResponse> = launchesListApi.getAllLaunches()

        return launches
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchMapper(it) }
    }

    internal fun clearDatabase() {
        appDatabaseQueries.removeAllLaunches()
    }

    internal fun getAllCachedLaunches(): List<RocketLaunch> {
        return appDatabaseQueries
            .selectAllLaunchesInfo()
            .executeAsList()
            .map {
                rocketLaunchMapper(it)
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
