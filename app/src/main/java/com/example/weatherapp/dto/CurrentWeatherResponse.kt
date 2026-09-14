package com.example.weatherapp.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val time: String,
    val interval: Int,

    @SerializedName("temperature_2m")
    val temperature2m: Double,

    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,

    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int,

    val precipitation: Double,

    @SerializedName("weather_code")
    val weatherCode: Int,

    @SerializedName("wind_speed_10m")
    val windSpeed10m: Double,

    @SerializedName("uv_index")
    val uvIndex: Double
)