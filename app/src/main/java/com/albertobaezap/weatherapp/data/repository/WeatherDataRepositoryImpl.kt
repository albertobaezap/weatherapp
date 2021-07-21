package com.albertobaezap.weatherapp.data.repository

import com.albertobaezap.weatherapp.data.sources.WeatherDataRemoteDataSource
import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.domain.repository.WeatherDataRepository

/**
 * Repository implementation.
 */
class WeatherDataRepositoryImpl(private val remoteDataSource: WeatherDataRemoteDataSource) :
    WeatherDataRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherData? =
        remoteDataSource.getWeatherRemote(latitude, longitude)
}