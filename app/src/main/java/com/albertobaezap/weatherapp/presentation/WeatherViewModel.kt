package com.albertobaezap.weatherapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertobaezap.weatherapp.domain.entities.WeatherData
import com.albertobaezap.weatherapp.domain.usecases.GetWeather
import com.albertobaezap.weatherapp.util.getRandomLatLon
import kotlinx.coroutines.launch

open class WeatherViewModel(
    private val getWeather: GetWeather
) : ViewModel() {
    val weatherData = MutableLiveData<WeatherData>()

    // Obtains a the weather for a random set of coordinates.
    open fun getRandomWeather() = viewModelScope.launch {
        val latLon = getRandomLatLon()
        weatherData.value = getWeather(latitude = latLon.latitude, longitude = latLon.longitude)!!
    }
}