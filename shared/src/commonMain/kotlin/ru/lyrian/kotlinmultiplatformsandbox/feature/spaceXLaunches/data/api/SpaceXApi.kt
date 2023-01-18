package ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.cache.RocketLaunch
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.data.mappers.RocketLaunchMapper

internal class SpaceXApi(private val rocketLaunchMapper: RocketLaunchMapper) {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    isLenient = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("HTTP-Client: $message")
                }
            }
            level = LogLevel.ALL
        }
    }

    suspend fun getAllLaunches(): List<RocketLaunch> {
        val result: List<RocketLaunchResponse> = httpClient
            .get("https://api.spacexdata.com/v5/launches")
            .body()

        val mappedLaunches = result
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchMapper(it) }

        println("KTORDEBUG: $result")

        return mappedLaunches
    }
}
