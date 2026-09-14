package com.example.weatherapp.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeatherResponse(

    val time: List<String>,

    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,

    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Int>,

    @SerializedName("weather_code")
    val weatherCode: List<Int>
)