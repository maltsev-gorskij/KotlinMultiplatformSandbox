package ru.lyrian.kotlinmultiplatformsandbox.data.dataSource.launchesApi

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.lyrian.kotlinmultiplatformsandbox.domain.launches.RocketLaunch

internal class LaunchesListApi(
    private val rocketLaunchApiMapper: RocketLaunchApiMapper,
    private val httpClient: HttpClient
) {
    internal suspend fun getAllLaunches(): List<RocketLaunch> {
        val result: List<RocketLaunchResponse> = httpClient
            .get("https://api.spacexdata.com/v5/launches")
            .body()

        return result
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchApiMapper(it) }
    }
}
