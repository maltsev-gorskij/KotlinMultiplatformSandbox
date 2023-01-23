package ru.lyrian.kotlinmultiplatformsandbox.feature.launches.data.dataSource


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RocketLaunchResponse(
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("details")
    val details: String?,
    @SerialName("flight_number")
    val flightNumber: Long,
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
    internal data class Links(
        @SerialName("article")
        val article: String?,
    )
}
