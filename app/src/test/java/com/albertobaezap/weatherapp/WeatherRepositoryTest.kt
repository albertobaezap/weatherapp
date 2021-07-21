package com.albertobaezap.weatherapp

import com.albertobaezap.weatherapp.data.network.WeatherApi
import com.albertobaezap.weatherapp.data.network.WeatherDataRemote
import com.albertobaezap.weatherapp.data.network.WeatherRemote
import com.albertobaezap.weatherapp.data.repository.WeatherDataRepositoryImpl
import com.albertobaezap.weatherapp.data.sources.WeatherDataRemoteDataSource
import com.albertobaezap.weatherapp.domain.repository.WeatherDataRepository
import com.albertobaezap.weatherapp.domain.usecases.GetWeather
import com.albertobaezap.weatherapp.util.LatLon
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mockk.coEvery
import io.mockk.mockk
import retrofit2.Response

class WeatherRepositoryTest: StringSpec() {

    private val api: WeatherApi = mockk(relaxed = true)

    private val dataSource: WeatherDataRemoteDataSource = WeatherDataRemoteDataSource(api)
    private val repository: WeatherDataRepository = WeatherDataRepositoryImpl(dataSource)
    private val getWeather: GetWeather = GetWeather(repository)

    init {
        "given a random set of coordinates, returns the proper data with empty city" {
            val randomCoords = LatLon(0.0, 0.0)
            val weatherData = WeatherDataRemote(
                city = "",
                weather = listOf(
                    WeatherRemote(
                    main = "Clouds",
                    description = "cloud sky",
                    icon = "10n"
                )
                )
            )
            coEvery {
                api.getWeatherData(any(), any())
            } returns Response.success(weatherData)

            val result = getWeather(randomCoords.latitude, randomCoords.longitude)
            result?.city shouldBe "(No specific city)"
        }
    }

}