package ru.lyrian.kotlinmultiplatformsandbox.feature.launchesList.data.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RocketLaunchResponse(
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("details")
    val details: String?,
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("links")
    val links: Links,
    @SerialName("name")
    val name: String,
    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String?,
    @SerialName("success")
    val success: Boolean?,
) {
    @Serializable
    data class Links(
        @SerialName("article")
        val article: String?,
    )
}
