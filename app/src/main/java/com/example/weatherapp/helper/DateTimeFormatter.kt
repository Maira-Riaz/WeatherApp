package com.example.weatherapp.helper

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatHourlyTime(time: String): String {
    val dateTime = LocalDateTime.parse(time)

    return dateTime.format(
        DateTimeFormatter.ofPattern("h a", Locale.getDefault())
    )
}

fun formatDay(date: String): String {
    val localDate = LocalDate.parse(date)

    return localDate.format(
        DateTimeFormatter.ofPattern("EEE", Locale.getDefault())
    )
}

fun formatSunTime(time: String): String {
    val dateTime = LocalDateTime.parse(time)

    return dateTime.format(
        DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())
    )
}
fun formatCurrentDate(): String {
    val today = LocalDate.now()

    return today.format(
        DateTimeFormatter.ofPattern(
            "EEEE, MMMM d",
            Locale.getDefault()
        )
    )
}