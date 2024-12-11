package com.hearity_capstone.hearity

import android.app.Application
import com.hearity_capstone.hearity.data.di.appModule
import com.hearity_capstone.hearity.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                networkModule
            )
        }
    }
}