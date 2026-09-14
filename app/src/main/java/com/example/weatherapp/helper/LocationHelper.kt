package com.example.weatherapp.helper

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")
fun fetchCurrentLocation(
    context: Context,
    onLocationReceived: (Double, Double) -> Unit,
    onLocationError: () -> Unit
) {

    val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
    ).addOnSuccessListener { location: Location? ->

        if (location != null) {

            onLocationReceived(
                location.latitude,
                location.longitude
            )

        } else {

            onLocationError()
        }
    }.addOnFailureListener {

        onLocationError()
    }
}

fun getCityName(
    context: Context,
    latitude: Double,
    longitude: Double
): String? {

    val geocoder = android.location.Geocoder(
        context,
        java.util.Locale.getDefault()
    )

    return try {
        val addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        )

        addresses?.firstOrNull()?.locality

    } catch (e: Exception) {
        null
    }
}