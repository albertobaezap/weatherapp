package com.albertobaezap.weatherapp.mocks

import androidx.lifecycle.viewModelScope
import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.domain.usecases.GetWeather
import com.albertobaezap.weatherapp.presentation.WeatherViewModel
import com.albertobaezap.weatherapp.util.LatLon
import io.mockk.mockk
import kotlinx.coroutines.launch

class WeatherViewModelMock : WeatherViewModel(mockk()) {

    override fun getRandomWeather() = viewModelScope.launch {
        weatherData.value = WeatherData(
            city = "Dubai",
            weather = "Clouds",
            icon = "10n",
            coordinates = LatLon(0.0, 0.0)
        )
    }
}