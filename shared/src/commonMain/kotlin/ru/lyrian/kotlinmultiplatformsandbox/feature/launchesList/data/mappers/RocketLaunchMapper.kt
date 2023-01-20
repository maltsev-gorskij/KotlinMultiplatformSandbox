package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.mappers

import ru.lyrian.kotlinmultiplatformsandbox.core.utils.toYear
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.api.RocketLaunchResponse
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.RocketLaunch

internal class RocketLaunchMapper {
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
