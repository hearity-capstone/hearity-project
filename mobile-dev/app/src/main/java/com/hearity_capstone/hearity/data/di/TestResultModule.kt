package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.data.repository.testResult.TestResultRepository
import com.hearity_capstone.hearity.data.repository.testResult.TestResultRepositoryImpl
import com.hearity_capstone.hearity.viewModel.TestResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testResultModule = module {
    single<TestResultRepository> { TestResultRepositoryImpl(get()) }

    viewModel { TestResultViewModel(get()) }
}