package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.BuildConfig
import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.repository.AuthRepository
import com.hearity_capstone.hearity.data.repository.AuthRepositoryImpl
import com.hearity_capstone.hearity.ui.screens.authentication.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    single<AuthRepository>(qualifier = named("AuthRepositoryImpl")) {
        AuthRepositoryImpl(get())
    }

    viewModel {
        AuthViewModel(get(qualifier = named("AuthRepositoryImpl")))
    }
}