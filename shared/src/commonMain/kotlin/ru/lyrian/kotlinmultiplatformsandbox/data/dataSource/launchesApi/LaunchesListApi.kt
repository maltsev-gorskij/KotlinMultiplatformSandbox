package ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi

import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.lyrian.kotlinmultiplatformsandbox.core.constants.KtorConstants
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.RocketLaunch

internal class LaunchesListApi(
    private val rocketLaunchApiMapper: RocketLaunchApiMapper,
    spaceXApiClientProvider: SpaceXApiClientProvider
) {
    private val client = spaceXApiClientProvider.client

    internal suspend fun getAllLaunches(): List<RocketLaunch> {
        val result: List<RocketLaunchResponse> = client
            .get(KtorConstants.SPACEX_LAUNCHES_ENDPOINT)
            .body()

        return result
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchApiMapper(it) }
    }
}
