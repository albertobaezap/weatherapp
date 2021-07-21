package com.albertobaezap.weatherapp.data.network

import com.google.gson.annotations.SerializedName

/*
 * Remote model that will be mapped to [WeatherData] entity.
 */
data class WeatherDataRemote(
    val weather: List<WeatherRemote>,
    @SerializedName("name") val city: String
)

data class WeatherRemote(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    val icon: String
)