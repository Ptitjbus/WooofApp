package com.mathisviollet.wooofapp.managers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point

object LocationService {
    lateinit var locationProvider: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun getUserLocation(context: Context, callback: (Point) -> Unit)  {
        locationProvider = LocationServices.getFusedLocationProviderClient(context)

        locationProvider.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    val lat = location.latitude
                    val long = location.longitude

                    callback(Point.fromLngLat(long, lat))
                }
            }
            .addOnFailureListener {
                Log.e("Location_error", "${it.message}")
            }
    }
}