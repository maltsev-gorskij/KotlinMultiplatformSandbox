package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache.RocketLaunch
import ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.mappers.RocketLaunchMapper

internal class LaunchesListApi(
    private val rocketLaunchMapper: RocketLaunchMapper,
    private val httpClient: HttpClient
) {
    suspend fun getAllLaunches(): List<RocketLaunch> {
        val result: List<RocketLaunchResponse> = httpClient
            .get("https://api.spacexdata.com/v5/launches")
            .body()

        return result
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchMapper(it) }
    }
}
