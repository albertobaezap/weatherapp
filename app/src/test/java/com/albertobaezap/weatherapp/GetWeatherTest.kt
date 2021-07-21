package com.albertobaezap.weatherapp

import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.domain.repository.WeatherDataRepository
import com.albertobaezap.weatherapp.domain.usecases.GetWeather
import com.albertobaezap.weatherapp.util.LatLon
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mockk.coEvery
import io.mockk.mockk

class GetWeatherTest : StringSpec() {
    private val repository: WeatherDataRepository = mockk()
    private val getWeather: GetWeather = GetWeather(repository)

    init {
        "given a random set of coordinates, returns the proper data" {
            val randomCoods = LatLon(0.0, 0.0)
            val weatherData = WeatherData(
                city = "Dubai",
                weather = "Clear sky",
                icon = "",
                coordinates = randomCoods
            )
            coEvery {
                repository.getWeather(any(), any())
            } returns weatherData

            val result = getWeather(randomCoods.latitude, randomCoods.longitude)
            result?.city shouldBe "Dubai"
        }
    }
}