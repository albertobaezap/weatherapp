package com.albertobaezap.weatherapp.domain.entities

import com.albertobaezap.weatherapp.util.LatLon

data class WeatherData(
    val weather: String,
    val city: String,
    val icon: String,
    val coordinates: LatLon
)