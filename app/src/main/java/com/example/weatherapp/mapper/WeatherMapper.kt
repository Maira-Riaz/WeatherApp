package com.example.weatherapp.mapper

import com.example.weatherapp.dto.CurrentWeather
import com.example.weatherapp.dto.DailyWeather
import com.example.weatherapp.dto.HourlyWeather
import com.example.weatherapp.dto.WeatherResponse
import com.example.weatherapp.helper.formatDay
import com.example.weatherapp.helper.formatHourlyTime
import com.example.weatherapp.helper.formatSunTime
import com.example.weatherapp.helper.getWeatherCondition

fun WeatherResponse.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        location = null,
        temperature = current.temperature2m.toInt(),
        highTemperature = daily.temperatureMax.firstOrNull()?.toInt(),
        lowTemperature = daily.temperatureMin.firstOrNull()?.toInt(),
        feelsLike = current.apparentTemperature.toInt(),
        condition = getWeatherCondition(current.weatherCode).name,
        windSpeed = current.windSpeed10m,
        humidity = current.relativeHumidity2m,
        uvIndex = current.uvIndex,
        precipitation = current.precipitation,
        sunrise = daily.sunrise.firstOrNull()?.let { formatSunTime(it) },
        sunset = daily.sunset.firstOrNull()?.let { formatSunTime(it) })
}

fun WeatherResponse.toHourlyWeather(): List<HourlyWeather> {
    return hourly.time.indices.map { index ->
        HourlyWeather(
            time = formatHourlyTime(hourly.time[index]),
            temperature = hourly.temperature2m[index].toInt(),
            precipitation = hourly.precipitationProbability[index],
            condition = getWeatherCondition(hourly.weatherCode[index])
        )
    }
}

fun WeatherResponse.toDailyWeather(): List<DailyWeather> {
    return daily.time.indices.map { index ->
        DailyWeather(
            day = formatDay(daily.time[index]),
            highTemperature = daily.temperatureMax[index].toInt(),
            lowTemperature = daily.temperatureMin[index].toInt(),
            precipitation = daily.precipitationProbabilityMax[index],
            dayCondition = getWeatherCondition(daily.weatherCode[index]),
            nightCondition = getWeatherCondition(daily.weatherCode[index])
        )
    }
}