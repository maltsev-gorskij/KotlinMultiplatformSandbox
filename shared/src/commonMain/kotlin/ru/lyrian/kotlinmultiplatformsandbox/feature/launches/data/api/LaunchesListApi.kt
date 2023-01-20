package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class LaunchesListApi(
    private val rocketLaunchApiMapper: RocketLaunchApiMapper,
    private val httpClient: HttpClient
) {
    suspend fun getAllLaunches(): List<RocketLaunch> {
        val result: List<RocketLaunchResponse> = httpClient
            .get("https://api.spacexdata.com/v5/launches")
            .body()

        return result
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchApiMapper(it) }
    }
}
