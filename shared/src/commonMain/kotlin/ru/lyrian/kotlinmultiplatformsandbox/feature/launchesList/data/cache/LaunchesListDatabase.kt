package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache

import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries

internal class LaunchesListDatabase(private val appDatabaseQueries: AppDatabaseQueries) {
    internal fun clearDatabase() {
        appDatabaseQueries.removeAllLaunches()
    }

    internal fun getAllLaunches(): List<RocketLaunch> {
        return appDatabaseQueries.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    private fun mapLaunchSelecting(
        flightNumber: Long,
        missionName: String,
        launchYear: Int,
        details: String?,
        launchSuccess: Boolean?,
        launchDateUTC: String,
        articleUrl: String?
    ): RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber.toInt(),
            missionName = missionName,
            launchYear = launchYear,
            launchDateUTC = launchDateUTC,
            details = details,
            launchSuccess = launchSuccess,
            articleUrl = articleUrl
        )
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
            flightNumber = launch.flightNumber.toLong(),
            missionName = launch.missionName,
            launchYear = launch.launchYear,
            details = launch.details,
            launchSuccess = launch.launchSuccess ?: false,
            launchDateUTC = launch.launchDateUTC,
            articleUrl = launch.articleUrl
        )
    }
}
