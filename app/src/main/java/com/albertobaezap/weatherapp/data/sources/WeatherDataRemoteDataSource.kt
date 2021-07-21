package com.albertobaezap.weatherapp.data.sources

import com.albertobaezap.weatherapp.data.network.WeatherApi
import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.util.LatLon

/**
 * Remote data source that will map remote model to local entity.
 */
class WeatherDataRemoteDataSource(private val weatherApi: WeatherApi) {
    suspend fun getWeatherRemote(latitude: Double, longitude: Double): WeatherData? {
        val response = weatherApi.getWeatherData(latitude, longitude)
        return if (response.isSuccessful) {
            with(response.body()!!) {

                val iconUrl = "https://openweathermap.org/img/wn/${weather.first().icon}@4x.png"

                WeatherData(
                    city = city.ifEmpty { "(No specific city)" },
                    weather = weather.first().description,
                    icon = iconUrl,
                    coordinates = LatLon(latitude, longitude)
                )
            }
        } else null
    }
}