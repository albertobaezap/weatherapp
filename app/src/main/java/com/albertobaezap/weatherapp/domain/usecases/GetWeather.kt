package com.albertobaezap.weatherapp.domain.usecases

import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.domain.repository.WeatherDataRepository

/**
 * Retrieves the weather for the given coordinates.
 */
class GetWeather(private val weatherDataRepository: WeatherDataRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherData? =
        weatherDataRepository.getWeather(latitude = latitude, longitude = longitude)
}