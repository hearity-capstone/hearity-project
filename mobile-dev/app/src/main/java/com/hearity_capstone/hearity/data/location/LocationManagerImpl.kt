package com.hearity_capstone.hearity.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_LOW_POWER
import com.google.android.gms.tasks.CancellationTokenSource
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
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        return suspendCancellableCoroutine { cont ->
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                PRIORITY_LOW_POWER,
                cancellationTokenSource.token
            )
                .addOnSuccessListener { location: Location? ->
                    if (location == null) {
                        cont.resumeWithException(LocationException.LocationNotAvailable())
                    } else {
                        cont.resume(location, onCancellation = { })
                    }
                }
                .addOnFailureListener { exception ->
                    cont.resumeWithException(LocationException.LocationRequestFailed(exception))
                }

            cont.invokeOnCancellation {
                cancellationTokenSource.cancel()
                cont.resumeWithException(LocationException.LocationCancelled())
            }
        }
    }
}

sealed class LocationException(message: String) : Exception(message) {
    class LocationNotAvailable : LocationException("Location inactive or unavailable")
    class LocationRequestFailed(cause: Throwable) :
        LocationException("Failed to request location updates: ${cause.message}")

    class LocationCancelled : LocationException("Location request was cancelled by the user")
}