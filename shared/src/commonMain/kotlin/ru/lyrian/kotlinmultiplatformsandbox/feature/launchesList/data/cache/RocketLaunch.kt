package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.cache

data class RocketLaunch(
    val flightNumber: Int,
    val missionName: String,
    val launchYear: Int,
    val launchDateUTC: String,
    val details: String?,
    val launchSuccess: Boolean?,
    val articleUrl: String?
)
