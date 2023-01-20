package ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi

import ru.lyrian.kotlinmultiplatformsandbox.core.utils.toYear
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.RocketLaunch

internal class RocketLaunchApiMapper {
    internal operator fun invoke(rocketLaunchResponse: RocketLaunchResponse): RocketLaunch =
        RocketLaunch(
            flightNumber = rocketLaunchResponse.flightNumber,
            missionName = rocketLaunchResponse.name,
            launchYear = rocketLaunchResponse.staticFireDateUtc?.toYear() ?: error("Launch date is null"),
            launchDateUTC = rocketLaunchResponse.staticFireDateUtc,
            details = rocketLaunchResponse.details ?: "",
            launchSuccess = rocketLaunchResponse.success ?: false,
            articleUrl = rocketLaunchResponse.links.article ?: ""
        )
}
