package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.api

import ru.lyrian.kotlinmultiplatformsandbox.core.utils.toYear
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class RocketLaunchApiMapper {
    operator fun invoke(rocketLaunchResponse: RocketLaunchResponse): RocketLaunch =
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
