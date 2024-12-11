package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.BuildConfig
import com.hearity_capstone.hearity.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    // Provide Singleton Retrofit
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }
}
