package com.hearity_capstone.hearity.data.repository.reminder

import com.hearity_capstone.hearity.data.storage.ReminderStorage
import java.time.LocalDate

class ReminderRepositoryImpl(private val reminderStorage: ReminderStorage) : ReminderRepository {
    override suspend fun saveReminder(date: LocalDate) {
        reminderStorage.saveReminder(date)
    }

    override suspend fun getReminderDate(): LocalDate? {
        return reminderStorage.getReminderDate()
    }
}