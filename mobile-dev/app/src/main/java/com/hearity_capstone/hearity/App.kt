package com.hearity_capstone.hearity

import android.app.Application
import com.hearity_capstone.hearity.data.di.authModule
import com.hearity_capstone.hearity.data.di.locationModule
import com.hearity_capstone.hearity.data.di.networkModule
import com.hearity_capstone.hearity.data.di.testResultModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                authModule,
                locationModule,
                testResultModule
            )
        }
    }
}