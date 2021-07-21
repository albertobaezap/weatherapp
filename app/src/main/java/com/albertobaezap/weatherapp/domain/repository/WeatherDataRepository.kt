package com.albertobaezap.weatherapp.domain.repository

import com.albertobaezap.weatherapp.domain.entities.WeatherData

interface WeatherDataRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherData?
}