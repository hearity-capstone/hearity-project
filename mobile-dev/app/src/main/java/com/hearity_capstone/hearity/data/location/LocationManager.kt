package com.hearity_capstone.hearity.data.location

import android.location.Location

interface LocationManager {
    suspend fun getLocationOnce(): Location?
}