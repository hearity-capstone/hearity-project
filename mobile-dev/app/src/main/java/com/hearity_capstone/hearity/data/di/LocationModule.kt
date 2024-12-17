package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.data.location.LocationManager
import com.hearity_capstone.hearity.data.location.LocationManagerImpl
import com.hearity_capstone.hearity.viewModel.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {
    // Provide singleton LocationManagerImpl
    single<LocationManager> {
        LocationManagerImpl(get())
    }

    // Provide singleton LocationViewModel
    viewModel { LocationViewModel(get()) }
}