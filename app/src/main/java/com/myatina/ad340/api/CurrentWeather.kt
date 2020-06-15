package com.myatina.ad340.api

import com.squareup.moshi.Json

data class Forecast(val temp: Float)
data class Cordinates(val lat:Float, val lon: Float)

data class CurrentWeather (
    val name: String,
    val coord: Cordinates,
    @field:Json(name="main") val forecast: Forecast
)