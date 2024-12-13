package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.BuildConfig
import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.api.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    // Provide singleton AuthInterceptor
    single { AuthInterceptor(get()) }

    // Provide singleton OkHttpClient with AuthInterceptor
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    // Provide Singleton Retrofit
    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }
}
