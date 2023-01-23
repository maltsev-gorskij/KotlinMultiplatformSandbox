package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.lyrian.kotlinmultiplatformsandbox.AppDatabaseQueries
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.dataSource.LaunchesListApi
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.dataSource.RocketLaunchResponse
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

internal class LaunchesRepository(
    private val launchesListApi: LaunchesListApi,
    private val appDatabaseQueries: AppDatabaseQueries,
    private val rocketLaunchMapper: RocketLaunchMapper
) {
    internal suspend fun getUpToDateLaunches(): List<RocketLaunch> {
        val launches: List<RocketLaunchResponse> = launchesListApi.getAllLaunches()

        return launches
            .filter { it.staticFireDateUtc != null }
            .map { rocketLaunchMapper(it) }
    }

    internal suspend fun clearDatabase() {
        withContext(Dispatchers.Default) {
            appDatabaseQueries.transaction {
                appDatabaseQueries.removeAllLaunches()
                appDatabaseQueries.removeAllFlickImages()
            }
        }
    }

    internal suspend fun getAllCachedLaunches(): List<RocketLaunch> {
        return withContext(Dispatchers.Default) {
            appDatabaseQueries.transactionWithResult {
                appDatabaseQueries
                    .getAllLaunches()
                    .executeAsList()
                    .map {
                        rocketLaunchMapper(
                            launchEntity = it,
                            flickrImagesUrls = appDatabaseQueries.getAllFlickrImagesByLaunchId(it.id).executeAsList()
                        )
                    }
            }
        }
    }

    internal suspend fun createLaunches(launches: List<RocketLaunch>) =
        withContext(Dispatchers.Default) {
            appDatabaseQueries.transaction {
                launches.forEach { launch ->
                    if (launch.flickrImagesUrls.isNotEmpty()) {
                        launch.flickrImagesUrls.forEach { imageUrl: String ->
                            appDatabaseQueries.insertFlickrImage(
                                launchId = launch.id,
                                imageUrl = imageUrl
                            )
                        }
                    }

                    appDatabaseQueries.insertLaunch(
                        flightNumber = launch.flightNumber,
                        missionName = launch.missionName,
                        launchYear = launch.launchYear,
                        details = launch.details,
                        launchSuccess = launch.launchSuccess ?: false,
                        launchDateUTC = launch.launchDateUTC,
                        articleUrl = launch.articleUrl,
                        id = launch.id,
                        patchImageUrl = launch.patchImageUrl
                    )
                }
            }
        }


    internal suspend fun getLaunchById(launchId: String): RocketLaunch =
        withContext(Dispatchers.Default) {
            appDatabaseQueries.transactionWithResult {
                rocketLaunchMapper(
                    launchEntity = appDatabaseQueries
                        .getLaunchById(launchId)
                        .executeAsOne(),
                    flickrImagesUrls = appDatabaseQueries
                        .getAllFlickrImagesByLaunchId(launchId)
                        .executeAsList()
                )
            }
        }
}
