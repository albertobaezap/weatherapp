package com.albertobaezap.weatherapp.util

import kotlin.random.Random

private const val MAX_ABSOLUTE_LATITUDE = 90.0
private const val MAX_ABSOLUTE_LONGITUDE = 180.0

data class LatLon(val latitude: Double, val longitude: Double)

fun getRandomLatLon(): LatLon =
    LatLon(
        latitude = Random.nextDouble(-MAX_ABSOLUTE_LATITUDE, MAX_ABSOLUTE_LATITUDE),
        longitude = Random.nextDouble(-MAX_ABSOLUTE_LONGITUDE, MAX_ABSOLUTE_LONGITUDE)
    )