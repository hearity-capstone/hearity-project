package com.hearity_capstone.hearity.viewModel

import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import com.hearity_capstone.hearity.data.location.LocationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val locationManager: LocationManager
) : ViewModel() {

    private val _locationUiState = MutableStateFlow<LocationUiState>(LocationUiState.Initial)

    val locationUiState: StateFlow<LocationUiState> = _locationUiState

    @OptIn(ExperimentalPermissionsApi::class)
    fun checkLocationPermission(context: Context, permissionState: PermissionState) {
        when {
            permissionState.status.isGranted -> {
                getLocation(context = context)
            }

            permissionState.status.shouldShowRationale -> {
                Toast.makeText(
                    context,
                    "Location permission is needed to get your location",
                    Toast.LENGTH_SHORT
                ).show()
                _locationUiState.value = LocationUiState.LocationPermissionsDenied(
                    isPermanentlyDenied = false
                )
            }

            !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> {
                permissionState.launchPermissionRequest()
            }
        }
    }

    fun getLocation(context: Context) {
        viewModelScope.launch {
            Log.d("LocationViewModel", "getLocation called")
            try {
                val location: Location? = locationManager.getLocationOnce()
                if (location != null) {
                    _locationUiState.value = LocationUiState.Success(location)
                    openGoogleMaps(context)
                }
                Log.d("LocationViewModel", "Location: $location")
            } catch (e: Exception) {
                Log.e("LocationViewModel", "Error getting location", e)
            }
        }
    }

    fun openGoogleMaps(context: Context) {
        val (longitude, latitude) = getLatitudeLongitude(_locationUiState.value)

        val uri = Uri.parse("geo:$latitude,$longitude?q=Dokter+THT+terdekat&radius=1000")

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.putExtra(
            Intent.EXTRA_REFERRER,
            Uri.parse("android-app://com.google.android.apps.maps")
        )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Google Maps not found!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getLatitudeLongitude(locationUiState: LocationUiState): Pair<Double?, Double?> {
        return when (locationUiState) {
            is LocationUiState.Success -> {
                Pair(locationUiState.location.latitude, locationUiState.location.longitude)
            }

            else -> {
                Pair(null, null)
            }
        }
    }

    sealed class LocationUiState {
        object Initial : LocationUiState()
        data class Success(val location: Location) : LocationUiState()
        data class LocationPermissionsDenied(val isPermanentlyDenied: Boolean) : LocationUiState()
    }
}



