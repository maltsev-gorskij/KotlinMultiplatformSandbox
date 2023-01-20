package ru.lyrian.kotlinmultiplatformsandbox.feature.common.data.cache

import ru.lyrian.kotlinmultiplatformsandbox.LaunchEntity
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class RocketLaunchDatabaseMapper {
    operator fun invoke(launchEntity: LaunchEntity): RocketLaunch =
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
