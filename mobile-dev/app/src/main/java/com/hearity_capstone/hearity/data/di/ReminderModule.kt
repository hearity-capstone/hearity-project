package com.hearity_capstone.hearity.data.di

import com.hearity_capstone.hearity.data.repository.reminder.ReminderRepository
import com.hearity_capstone.hearity.data.repository.reminder.ReminderRepositoryImpl
import com.hearity_capstone.hearity.data.storage.ReminderStorage
import com.hearity_capstone.hearity.viewModel.ReminderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reminderModule = module {
    // Provide singleton ReminderStorage
    single<ReminderStorage> { ReminderStorage(get()) }

    // Provide singleton ReminderRepository
    single<ReminderRepository> { ReminderRepositoryImpl(get()) }

    // Provide ReminderViewModel
    viewModel { ReminderViewModel(get()) }

}