package ru.lyrian.kotlinmultiplatformsandbox.data.repository.launches

import ru.lyrian.kotlinmultiplatformsandbox.LaunchEntity
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.RocketLaunch

internal class RocketLaunchDatabaseMapper {
    internal operator fun invoke(launchEntity: LaunchEntity): RocketLaunch =
        RocketLaunch(
            flightNumber = launchEntity.flightNumber,
            missionName = launchEntity.missionName,
            launchYear = launchEntity.launchYear,
            launchDateUTC = launchEntity.launchDateUTC,
            details = launchEntity.details,
            launchSuccess = launchEntity.launchSuccess,
            articleUrl = launchEntity.articleUrl
        )
}
