package com.hearity_capstone.hearity.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class LocationManagerImpl(
    private val context: Context
) : LocationManager {

    private val client: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    @Throws
    override suspend fun getLocationOnce(): Location? {

        return suspendCancellableCoroutine<Location> { cont ->
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_LOW_POWER, 5000L)
                .setMinUpdateDistanceMeters(500f)
                .build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    result.lastLocation?.let { location ->
                        cont.resume(location, onCancellation = {
                            cont.resumeWithException(LocationException.LocationCancelled())
                        })
                        client.removeLocationUpdates(this)
                    } ?: run {
                        cont.resumeWithException(LocationException.NullLocation())
                        client.removeLocationUpdates(this)
                    }
                }

                override fun onLocationAvailability(availability: LocationAvailability) {
                    if (!availability.isLocationAvailable) {
                        cont.resumeWithException(LocationException.LocationNotAvailable())
                        client.removeLocationUpdates(this)
                    }
                }
            }

            try {
                client.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
                    .addOnFailureListener { exception ->
                        cont.resumeWithException(LocationException.LocationRequestFailed(exception))
                    }
            } catch (e: Exception) {
                cont.resumeWithException(LocationException.LocationRequestFailed(e))
            }

            cont.invokeOnCancellation {
                client.removeLocationUpdates(locationCallback)
                cont.resumeWithException(LocationException.LocationCancelled())
            }
        }

    }
}

sealed class LocationException(message: String) : Exception(message) {
    class LocationNotAvailable : LocationException("Location is not available")
    class LocationRequestFailed(cause: Throwable) :
        LocationException("Failed to request location updates: ${cause.message}")

    class NullLocation : LocationException("Received null location")
    class LocationCancelled : LocationException("Location request was cancelled by the user")
}