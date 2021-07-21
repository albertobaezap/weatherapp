package com.albertobaezap.weatherapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for the Openweathermap API.
 */
interface WeatherApi {

    /**
     * Retrieve weather by geographic coordinates.
     */
    @GET("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response<WeatherDataRemote>
}